package tn.esprit.bank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.dto.PasswordDto;
import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.MoralUser;
import tn.esprit.bank.exception.UserNotFoundException;
import tn.esprit.bank.model.GenericResponse;
import tn.esprit.bank.model.MailTemplate;
import tn.esprit.bank.model.user.ChangePasswordRequest;
import tn.esprit.bank.security.ISecurityUserService;
import tn.esprit.bank.service.RoleService;
import tn.esprit.bank.service.UserService;
import tn.esprit.bank.util.Constants;
import tn.esprit.bank.util.JmsSender;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping(Constants.APP_ROOT + "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    @Autowired
    private Environment env;


    @Autowired
    JmsSender jmsSender;

    @Autowired
    private ISecurityUserService securityUserService;

    @PostMapping("/register")
    private ResponseEntity<AbstractUser> saveUser(@RequestBody MoralUser user) {
        if (userService.getUserByCin(user.getCin()) != null) {
            return new ResponseEntity("Cin already exists", HttpStatus.FORBIDDEN);
        }
        if (!userService.checkEmailValid(user.getEmail())) {
            return new ResponseEntity("Email address invalid", HttpStatus.FORBIDDEN);

        }
        user.setEnabled(false);
        user.setRoles(Sets.newHashSet(roleService.getGuestRole()));
        return new ResponseEntity<AbstractUser>(userService.saveUser(user), HttpStatus.OK);

    }

    @PutMapping("/activate/{cin}")
    private ResponseEntity<AbstractUser> activateUser(@PathVariable("cin") Long cin) {
        AbstractUser user = userService.getUserById(cin);
        if (user == null) {
            return new ResponseEntity("User doesn't exist !", HttpStatus.BAD_REQUEST);
        } else {
            user.setEnabled(true);
            userService.saveUser(user);
            return new ResponseEntity("User CIN : " + user.getCin().toString() + "account is activated", HttpStatus.OK);
        }
    }

    @PutMapping("/deactivate/{cin}")
    private ResponseEntity<AbstractUser> deactivateUser(@PathVariable("cin") Long cin) {
        AbstractUser user = userService.getUserById(cin);
        if (user == null) {
            return new ResponseEntity("User doesn't exist !", HttpStatus.BAD_REQUEST);
        } else {
            user.setEnabled(false);
            userService.saveUser(user);
            return new ResponseEntity("User CIN : " + user.getCin().toString() + "account is activated", HttpStatus.OK);
        }
    }

    @PutMapping("/changePassword")
    private ResponseEntity<AbstractUser> changerUserPassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getNewPassword())) {
            return new ResponseEntity("Old password cannot be new password !", HttpStatus.BAD_REQUEST);
        }
        if (changePasswordRequest.getNewPassword() == changePasswordRequest.getConfirmNewPassword()) {
            return new ResponseEntity("New password and confirmation password don't match !", HttpStatus.BAD_REQUEST);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            AbstractUser connectedUser = (AbstractUser) principal;
            if (userService.checkPasswordMatch(changePasswordRequest.getOldpassword(), connectedUser)) {
                connectedUser.setPassword(changePasswordRequest.getNewPassword());
                userService.saveUser(connectedUser);
                return new ResponseEntity("Password changed successfully !", HttpStatus.OK);
            } else {
                return new ResponseEntity("Password invalid !", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity("Operation failed !", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/resetPassword")
    public GenericResponse resetPassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail) {
        AbstractUser user = userService.getUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException();
        }
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        MailTemplate mailTemplate = constructResetTokenEmail(getAppUrl(request),
                request.getLocale(), token, user);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jmsSender.sendMessagePointToPoint(objectMapper.writeValueAsString(mailTemplate), "SendSigningMailQueue");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        return new GenericResponse(
//                messages.getMessage("", null,
//                        request.getLocale()));
        return new GenericResponse("You should receive an Password Reset Email shortly");
    }


    @PostMapping("/sendMail")
    public void sendMail(HttpServletRequest request,
                         @RequestParam("email") String userEmail) {
        MailTemplate mailTemplate = new MailTemplate("haithemby@gmail.com", "test smtp", "haythem.benyahia@esprit.tn", "body");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jmsSender.sendMessagePointToPoint(objectMapper.writeValueAsString(mailTemplate), "SendSigningMailQueue");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private MailTemplate constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final AbstractUser user) {
        String body = "Reset password" + " \r\n" + contextPath + "/api/user/changePassword?token=" + token;
        return new MailTemplate(user.getEmail(), "Reset subject", "", body);

    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    // Save password
    @PostMapping("/savePassword")
    public GenericResponse savePassword(final Locale locale, @Valid PasswordDto passwordDto) {

        final String result = securityUserService.validatePasswordResetToken(passwordDto.getToken());

        if (result != null) {
            return new GenericResponse("Invalid token.");
        }

        Optional<AbstractUser> user = userService.getUserByPasswordResetToken(passwordDto.getToken());
        if (user.isPresent()) {
            userService.changeUserPassword(user.get(), passwordDto.getNewPassword());
            return new GenericResponse("Password reset successfully");
        } else {
            return new GenericResponse("Invalid token");
        }
    }

}

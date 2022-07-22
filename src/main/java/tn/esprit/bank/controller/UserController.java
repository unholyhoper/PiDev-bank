package tn.esprit.bank.controller;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.MoralUser;
import tn.esprit.bank.entity.Role;
import tn.esprit.bank.model.user.ChangePasswordRequest;
import tn.esprit.bank.service.RoleService;
import tn.esprit.bank.service.UserService;
import tn.esprit.bank.util.Constants;

import java.util.HashSet;

@RestController()
@RequestMapping(Constants.APP_ROOT + "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping("/register")
    private ResponseEntity<AbstractUser> saveUser(@RequestBody MoralUser user) {
        if (userService.getUserByCin(user.getCin()) != null) {
            return new ResponseEntity("Cin already exists", HttpStatus.FORBIDDEN);
        }
        if (!userService.checkEmailValid(user.getEmail())){
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


}

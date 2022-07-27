package tn.esprit.bank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.PasswordResetToken;
import tn.esprit.bank.exception.UserNotFoundException;
import tn.esprit.bank.model.GenericResponse;
import tn.esprit.bank.model.MailTemplate;
import tn.esprit.bank.service.UserService;
import tn.esprit.bank.util.Constants;
import tn.esprit.bank.util.JmsSender;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(Constants.APP_ROOT + "/user")
public class UserMVCController {

    @Autowired
    UserService userService;

    @Autowired
    private MessageSource messages;

    @Autowired
    JmsSender jmsSender;



    @GetMapping("/updatePassword")
    public ModelAndView updatePassword(final HttpServletRequest request, final ModelMap model, @RequestParam("messageKey") final Optional<String> messageKey) {
        Locale locale = request.getLocale();
        model.addAttribute("lang", locale.getLanguage());
        messageKey.ifPresent(key -> {
                    String message = messages.getMessage(key, null, locale);
                    model.addAttribute("message", message);
                }
        );

        return new ModelAndView("updatePassword", model);
    }
    @GetMapping("/changePassword")
    public ModelAndView changePassword(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) {
        final Locale locale = request.getLocale();

        final PasswordResetToken passToken = userService.getPasswordResetToken(token);
//        if (passToken == null || passToken.getUser().getId() != id) {
//            final String message = messages.getMessage("auth.message.invalidToken", null, locale);
//            model.addAttribute("message", message);
//            return "redirect:/login.html?lang=" + locale.getLanguage();
//        }
//
//        final Calendar cal = Calendar.getInstance();
//        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
//            return "redirect:/login.html?lang=" + locale.getLanguage();
//        }

        final Authentication auth = new UsernamePasswordAuthenticationToken(passToken.getUser(), null, userService.loadUserByUsername(passToken.getUser().getUsername()).getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updatePassword.html");
        return modelAndView;
//        return "redirect:/template/updatePassword.html?lang=" + locale.getLanguage();
    }



}

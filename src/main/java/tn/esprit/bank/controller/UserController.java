package tn.esprit.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.MoralUser;
import tn.esprit.bank.repository.UserRepository;
import tn.esprit.bank.service.UserService;
import tn.esprit.bank.util.Constants;

@RestController()
@RequestMapping(Constants.APP_ROOT + "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    private ResponseEntity<AbstractUser> saveBook(@RequestBody MoralUser user) {
        if (userService.getUserByCin(user.getCin()) != null) {
            return new ResponseEntity("Cin already exists", HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<AbstractUser>(userService.saveUser(user), HttpStatus.OK);
        }
    }
}

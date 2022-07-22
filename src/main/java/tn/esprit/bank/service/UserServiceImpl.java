package tn.esprit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.MoralUser;
import tn.esprit.bank.entity.Role;
import tn.esprit.bank.repository.UserRepository;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private Pattern regexPattern;
    private Matcher regMatcher;

    @Autowired
    UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    //todo make correct implementation
    @Transactional
    public AbstractUser loadUserByUsername(String username) {
        return userRepository.findUserByUserName(username);
    }

    @Override
    public AbstractUser saveUser(AbstractUser user) {
        // TODO Auto-generated method stub
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public AbstractUser getUserByCin(Long id) {
        return userRepository.findUserByCin(id);
    }

    @Override
    public AbstractUser getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public boolean checkPasswordMatch(String password, AbstractUser user) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public boolean checkEmailValid(String email) {
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher = regexPattern.matcher(email);
        if (regMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

}

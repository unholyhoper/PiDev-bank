package tn.esprit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.MoralUser;
import tn.esprit.bank.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

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
}

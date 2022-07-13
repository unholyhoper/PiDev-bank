package tn.esprit.bank.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tn.esprit.bank.entity.AbstractUser;

public interface UserService extends UserDetailsService {
    public AbstractUser loadUserByUsername(String username);

    public AbstractUser saveUser(AbstractUser user);

    public AbstractUser getUserByCin(Long id);

}

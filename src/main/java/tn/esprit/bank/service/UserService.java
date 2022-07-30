package tn.esprit.bank.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.PasswordResetToken;

import java.util.Collection;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    public AbstractUser loadUserByUsername(String username);

    public AbstractUser saveUser(AbstractUser user);

    public AbstractUser getUserByCin(Long cin);

    public AbstractUser getUserById(Long id);

    public boolean checkPasswordMatch(String password,AbstractUser user);

    public boolean checkEmailValid(String email);

    public AbstractUser getUserByEmail(String email);

    void createPasswordResetTokenForUser(AbstractUser user, String token);

    PasswordResetToken getPasswordResetToken(String token);

    Optional<AbstractUser> getUserByPasswordResetToken(String token);

    void changeUserPassword(AbstractUser user, String password);

    Collection<AbstractUser> getAllUsers();


}

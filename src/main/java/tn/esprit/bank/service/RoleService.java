package tn.esprit.bank.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.Role;

public interface RoleService {
    public Role getGuestRole();
    public Role getUserRole();
    public Role getBankerRole();
}

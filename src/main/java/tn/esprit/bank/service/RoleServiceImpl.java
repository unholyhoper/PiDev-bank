package tn.esprit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.bank.entity.Role;
import tn.esprit.bank.repository.RoleRepository;
import tn.esprit.bank.util.Constants;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getGuestRole() {
        return roleRepository.findRoleByName(Constants.GUEST);
    }

    @Override
    public Role getUserRole() {
        return roleRepository.findRoleByName(Constants.USER);
    }

    @Override
    public Role getBankerRole() {
        return roleRepository.findRoleByName(Constants.BANKER);
    }
}

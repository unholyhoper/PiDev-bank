package tn.esprit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String name);

}

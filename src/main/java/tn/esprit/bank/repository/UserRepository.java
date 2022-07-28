package tn.esprit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.AccountRequest;
import tn.esprit.bank.entity.Role;

import java.util.Collection;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<AbstractUser, Long> {
    AbstractUser findUserByCin(Long cin);

    public AbstractUser findUserByUserName(String username);

    public AbstractUser findUserById(Long id);

    public AbstractUser findUserByEmail(String email);

}

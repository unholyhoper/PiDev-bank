package tn.esprit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {

}

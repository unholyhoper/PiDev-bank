package tn.esprit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.BankAccount;

import java.math.BigDecimal;
import java.util.Optional;


@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {

    Optional<BankAccount> findBankAccountByAccountNumber(BigDecimal accountNumber);



}

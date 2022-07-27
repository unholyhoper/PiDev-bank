package tn.esprit.bank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.enumeration.TransactionStatus;

import java.util.List;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {


    List<Transaction> findAllByStatus(TransactionStatus status);
}

package tn.esprit.bank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.Transaction;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {


}

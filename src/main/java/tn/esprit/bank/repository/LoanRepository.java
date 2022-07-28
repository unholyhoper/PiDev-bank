package tn.esprit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.Loan;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    //all crud dataBase methods

}

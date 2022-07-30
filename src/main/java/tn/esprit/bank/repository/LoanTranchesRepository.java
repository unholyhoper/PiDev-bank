package tn.esprit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.LoanTranches;

import java.util.List;

@Repository
public interface LoanTranchesRepository extends JpaRepository<LoanTranches, Long> {
    //all crud dataBase methods
    @Query(value = "select ad from LoanTranches ad where ad.loan.id = :id")
    List<LoanTranches> findAllByLoanId(Long id);

    @Query(value = "select ad from LoanTranches ad where ad.date = :date")
    List<LoanTranches> findAllByLoanTranchDate(String date);
}

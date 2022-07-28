package tn.esprit.bank.service;

import tn.esprit.bank.entity.Loan;
import tn.esprit.bank.entity.LoanTranches;

import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ILoanService {
    Loan createLoanRequest(Loan loan) throws Exception;

    List<Loan> getAllLoansRequest();

    List<Loan> getAllLoanByIdRequest(Long id);

    void deleteLoanByIdRequest(Long id);
    void updateLoanTranchStatus ();

    Loan updateLoanRequest(Long id, Loan newLoan);

    List<Loan> getLoansByAccountId (Long accountId);
    void export(HttpServletResponse response, Long id) throws IOException;


}

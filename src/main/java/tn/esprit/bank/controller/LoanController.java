package tn.esprit.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.entity.Loan;
import tn.esprit.bank.repository.CurrentAccountRepository;
import tn.esprit.bank.service.LoanServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    @Autowired
    private LoanServiceImpl loanService;
    @Autowired
    CurrentAccountRepository currentAccountRepository;

    //build get all loans Rest API
    @GetMapping("/getAllLoans")
    public ResponseEntity<List<Loan>> getAllLoans() {
        try {
//            ArrayList list = new ArrayList<Loan>();
//            list.addAll(loanService.getAllLoansRequest());
            return ResponseEntity.ok(loanService.getAllLoansRequest());
        } catch (Exception e) {
            ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().build();
        }
    }

    //build create loan Rest API
    @PostMapping("/createLoan")
    public Object createLoan(@RequestBody Loan loan) {
        try {
            loanService.createLoanRequest(loan);
            return ResponseEntity.ok(loan);
        } catch (Exception e) {
            ResponseEntity.badRequest().build();
            return e.getMessage();
        }
    }

    //build get loan by id Rest API
    @GetMapping("/getLoanById")
    public List<Loan> getLoanById(Long id) {
        try {
            List<Loan> loan = loanService.getAllLoanByIdRequest(id);
            return loan;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    //build delete loan Rest API
    @DeleteMapping("/deleteLoanById")
    public ResponseEntity<String> deleteLoanById(Long id) {
        try {
            loanService.deleteLoanByIdRequest(id);
            return ResponseEntity.ok("Loan " + id + " deleted");
        } catch (Exception e) {
            ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().build();
        }
    }

    //build update loan Rest API
    @PutMapping("/updateLoan")
    public ResponseEntity<Loan> updateLoan(Long id, @RequestBody Loan newLoan) {
        try {
            Loan loanRes = loanService.updateLoanRequest(id, newLoan);
            return ResponseEntity.ok(loanRes);
        } catch (Exception e) {
            ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response , Long id) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.loanService.export(response ,id);
    }

    //build get loans of an account Rest API
}

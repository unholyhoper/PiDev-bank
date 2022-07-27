package tn.esprit.bank.controller;

import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.batch.TransactionPeriodicBatch;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.entity.TransactionPeriodic;
import tn.esprit.bank.enumeration.TransactionStatus;
import tn.esprit.bank.service.TransactionService;
import tn.esprit.bank.vo.TransactionVO;

import java.util.List;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionPeriodicBatch transactionPeriodicBatch;


    @GetMapping("/getAllTransactions")
    public List<Transaction> getAllTransactions() {


        return transactionService.findAllTransactions();
    }


    @PostMapping("/createTransactionVirement")
    public ResponseEntity createTransactionVirement(@RequestBody TransactionVO transactionVo) {
        try {

            return ResponseEntity.ok(transactionService.createTransactionVirement(transactionVo));

        } catch (RuntimeException exception) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }

    }

    @PostMapping("/createTransactionVersement")
    public ResponseEntity createTransactionVersement(@RequestBody TransactionVO transactionVo) {
        try {

            return ResponseEntity.ok(transactionService.createTransactionVersement(transactionVo));

        } catch (RuntimeException exception) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PostMapping("/createTransactionRetrait")
    public ResponseEntity createTransactionRetrait(@RequestBody TransactionVO transactionVo) {
        try {

            return ResponseEntity.ok(transactionService.createTransactionRetrait(transactionVo));

        } catch (RuntimeException exception) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @GetMapping("/getTransaction/{transactionId}")
    public ResponseEntity getTransaction(@PathVariable("transactionId") Long transactionId) {
        try {

            return ResponseEntity.ok(transactionService.findTransactionById(transactionId));


        } catch (RuntimeException exception) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }


    @PutMapping("/updateTransaction/{transactionId}")
    public ResponseEntity updateTransaction(@PathVariable("transactionId") Long transactionId, @RequestParam("status") String status) {

        try {
            return ResponseEntity.ok(transactionService.updateTransaction(transactionId, status));


        } catch (RuntimeException exception) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}









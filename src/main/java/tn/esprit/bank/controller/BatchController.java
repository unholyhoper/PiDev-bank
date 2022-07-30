package tn.esprit.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.batch.TransactionPeriodicBatch;
import tn.esprit.bank.batch.ValidateTransactionBatch;

@RestController
@RequestMapping("/api/batch")
public class BatchController {

    @Autowired
    TransactionPeriodicBatch transactionPeriodicBatch;

    @Autowired
    ValidateTransactionBatch validateTransactionBatch;


    @PostMapping("/transactionPeriodic")
    public ResponseEntity transactionPeriodic() {
        try {

            transactionPeriodicBatch.createVirementAutomatique();
            return ResponseEntity.ok("");


        } catch (RuntimeException exception) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PostMapping("/validateTransaction")
    public ResponseEntity validateTransaction() {
        try {

            validateTransactionBatch.validateTransaction();
            return ResponseEntity.ok("");


        } catch (RuntimeException exception) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }


}

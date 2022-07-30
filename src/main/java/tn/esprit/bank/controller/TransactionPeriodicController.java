package tn.esprit.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.service.TransactionPeriodicService;
import tn.esprit.bank.vo.TransactionPeriodicVO;

@RestController
@RequestMapping("/api/transactionPeriodic")
public class TransactionPeriodicController {

    @Autowired
    private TransactionPeriodicService transactionPeriodicService;

    @PostMapping("/createTransactionPeriodic")
    public ResponseEntity createTransactionPeriodic(@RequestBody TransactionPeriodicVO transactionPeriodicVo) {


        try {
            return ResponseEntity.ok(transactionPeriodicService.createPeriodicTransaction(transactionPeriodicVo));


        } catch (RuntimeException exception) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }

    }

    @PutMapping("/cancelTransactionPeriodic/{transactionId}")
    public ResponseEntity cancelTransactionPeriodic(@PathVariable("transactionId") Long transactionId) {

        try {
            return ResponseEntity.ok(transactionPeriodicService.cancelTransactionPeriodic(transactionId));


        } catch (RuntimeException exception) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}

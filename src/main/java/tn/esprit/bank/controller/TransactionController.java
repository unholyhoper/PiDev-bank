package tn.esprit.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.enumeration.TransactionStatus;
import tn.esprit.bank.service.TransactionService;
import tn.esprit.bank.vo.TransactionVO;

import java.util.List;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @GetMapping("/getAllTransactions")
    public List<Transaction> getAllTransactions() {


        return transactionService.findAllTransactions();
    }


    @PostMapping("/createTransaction")
    public Transaction createTransaction(@RequestBody TransactionVO transactionVo) {
        return transactionService.createTransaction(transactionVo);

    }


    @GetMapping("/getTransaction/{transactionId}")
    public Transaction getTransaction(@PathVariable("transactionId") Long transactionId) {
        return transactionService.findTransactionById(transactionId).orElse(null);
    }


    @PutMapping("/updateTransaction/{transactionId}")
    public Transaction updateTransaction(@PathVariable("transactionId") Long transactionId, @RequestParam("status") String status) {


        return transactionService.updateTransaction(transactionId, status);

    }
}









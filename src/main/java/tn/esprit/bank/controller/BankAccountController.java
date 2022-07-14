package tn.esprit.bank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.entity.BankAccount;
import tn.esprit.bank.entity.CurrentAccount;
import tn.esprit.bank.service.BankAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/bankAccount")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;


    @GetMapping("/getAllBankAccounts")
    public List<CurrentAccount> getAllBankAccounts(){
        return bankAccountService.findAllCurrentAccounts();
    }


    @PostMapping("/createBankAccount")
    public  BankAccount createBankAccount(@RequestBody BankAccount bankAccount){
        bankAccountService.createBankAccount(bankAccount);

        return bankAccount;
    }



}

package tn.esprit.bank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.entity.BankAccount;
import tn.esprit.bank.entity.CurrentAccount;
import tn.esprit.bank.entity.SavingAccount;
import tn.esprit.bank.service.BankAccountService;
import tn.esprit.bank.util.Constants;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_ROOT +"/bankAccount")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;


    @GetMapping("/getAllBankAccounts")
    public ResponseEntity<List<BankAccount>> getAllBankAccounts(){
        try {
            return ResponseEntity.ok(bankAccountService.findAllAccounts());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

   @GetMapping("/getAllCurrentAccounts")
    public ResponseEntity<List<CurrentAccount>> getAllCurrentAccounts(){
        try {
            return ResponseEntity.ok(bankAccountService.findAllCurrentAccounts());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

   @GetMapping("/getAllSavingAccounts")
    public ResponseEntity<List<SavingAccount>> getAllSavingAccounts(){
        try {
            return ResponseEntity.ok(bankAccountService.findAllSavingAccounts());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }



    @GetMapping("/getCurrentBankAccountById")
    public ResponseEntity<CurrentAccount> getCurrentAccountById(@RequestParam Long id){
        try {
            return  ResponseEntity.ok(bankAccountService.findCurrentAccountById(id));
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

   @GetMapping("/getSavingBankAccountById")
    public ResponseEntity<SavingAccount> getSavingAccountById(@RequestParam Long id){
        try {
            return  ResponseEntity.ok(bankAccountService.findSavingAccountById(id));
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


    @PostMapping("/createCurrentAccount")
    public ResponseEntity<CurrentAccount> createCurrentBankAccount(@RequestParam Long accountRequestId){
    CurrentAccount currentAccount =   bankAccountService.createCurrentAccount(accountRequestId);
           if(currentAccount!=null){
               return ResponseEntity.ok(currentAccount);
           }else {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
           }
    }


    @PostMapping("/createSavingAccount")
    public ResponseEntity<SavingAccount> createSavingBankAccount(@RequestParam Long accountRequestId){
    SavingAccount savingAccount =   bankAccountService.createSavingAccount(accountRequestId);
           if(savingAccount!=null){
               return ResponseEntity.ok(savingAccount);
           }else {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
           }
    }








}

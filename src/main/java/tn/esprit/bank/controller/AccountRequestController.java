package tn.esprit.bank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.entity.AccountRequest;
import tn.esprit.bank.service.AccountRequestServiceImpl;

@RestController
@RequestMapping("/api/accountRequest")
public class AccountRequestController {

    @Autowired
    AccountRequestServiceImpl accountRequestService;

    @PostMapping("/createAccountRequest")
    public ResponseEntity<AccountRequest> createAccountRequest(@RequestBody AccountRequest accountRequest){
     try {
         accountRequestService.createAccountRequest(accountRequest);
         return ResponseEntity.ok(accountRequest);
     }catch (Exception e){
         ResponseEntity.badRequest().build();
         return ResponseEntity.badRequest().build();
     }


    }



}

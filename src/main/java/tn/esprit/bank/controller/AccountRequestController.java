package tn.esprit.bank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.AccountRequest;
import tn.esprit.bank.entity.MoralUser;
import tn.esprit.bank.enumeration.AccountRequestStatus;
import tn.esprit.bank.repository.UserRepository;
import tn.esprit.bank.service.AccountRequestServiceImpl;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/accountRequest")
public class AccountRequestController {

    @Autowired
    AccountRequestServiceImpl accountRequestService;



    @GetMapping("/getAllAccountRequest")
    public List<AccountRequest> getAllAccountRequests(){
        return  accountRequestService.getAllAccountRequests();
    }

    @GetMapping("/getAccountRequest")
    public AccountRequest getAccountRequestById(@RequestParam Long id){
        return  accountRequestService.getAccountRequestById(id);
    }

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

    @DeleteMapping("/deleteAccountRequest")
    public ResponseEntity<String> deleteAccountRequest(@RequestParam Long id){
            int deleteStatus = accountRequestService.deleteAccountRequestById(id);
            switch (deleteStatus){
                case 0:
                    return ResponseEntity.status(HttpStatus.OK).build();
                case 1:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: AccountRequest with Id "+
                            id.toString()+" not found");
                case 2:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: An Active bank account " +
                            "is refrencing this Account request");
                default:
                    break;

            }
            return ResponseEntity.ok().build();
    }

    @PutMapping("/patchAccountRequest")
    public ResponseEntity<AccountRequest> putAccountRequest(@RequestBody AccountRequest accountRequest){
        try {
            accountRequestService.patchAccountRequest(accountRequest);
            return ResponseEntity.ok(accountRequest);
        }catch (Exception e){
            ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/setAccountRequestStatus")
    public ResponseEntity<AccountRequest> createAccountRequest(@RequestParam  Long id, @RequestParam AccountRequestStatus accountRequestStatus){
        try {
            accountRequestService.changeAccountRequestStatusById(id,accountRequestStatus);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/sendSigningMail")
    public ResponseEntity<String> sendSigningMail(@RequestParam Long accountRequestId, @RequestParam Date signingDate) {
            String x = accountRequestService.sendSigningRequest(accountRequestId,signingDate);
            return ResponseEntity.ok(x);
    }




}

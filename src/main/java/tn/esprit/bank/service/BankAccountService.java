package tn.esprit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.bank.entity.*;
import tn.esprit.bank.repository.AccountRequestRepository;
import tn.esprit.bank.repository.BankAccountRepository;
import tn.esprit.bank.repository.CurrentAccountRepository;
import tn.esprit.bank.repository.SavingAccountRepository;
import tn.esprit.bank.service.externalService.AccountNumberGenerator;
import tn.esprit.bank.util.ApplicationTiming;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService implements IBankAccountService {

    @Autowired
    CurrentAccountRepository currentAccountRepository;

    @Autowired
    SavingAccountRepository savingAccountRepository;


    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    AccountNumberGenerator accountNumberGenerator;

    @Autowired
    AccountRequestRepository accountRequestRepository;

    @Autowired
    ApplicationTiming applicationTiming;



    @Override
    public List<BankAccount> findAllAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public List<CurrentAccount> findAllCurrentAccounts() {
      return   currentAccountRepository.findAll();
    }

    @Override
    public List<SavingAccount> findAllSavingAccounts() {
        return savingAccountRepository.findAll();
    }

    @Override
    public CurrentAccount findCurrentAccountById(Long id) {
        if(currentAccountRepository.findById(id).isPresent()) {
            return currentAccountRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public SavingAccount findSavingAccountById(Long id) {
        if(savingAccountRepository.findById(id).isPresent()) {
            return savingAccountRepository.findById(id).get();
        }else {
            return null;
        }
    }

    @Override
    public CurrentAccount createCurrentAccount(Long accountRequestId) {
        Optional<AccountRequest> accountRequestOptional = accountRequestRepository.findById(accountRequestId);
        if(accountRequestOptional.isPresent()){
            AccountRequest accountRequest = accountRequestOptional.get();
            System.out.println(accountNumberGenerator.generateAccountNumber());
            CurrentAccount currentAccount = new CurrentAccount(accountNumberGenerator.generateAccountNumber(),
                    accountRequest.getUser(),applicationTiming.getInstantDateTime(),BigDecimal.TEN,
                    accountRequest,accountRequest.getInterestRate());
            currentAccountRepository.save(currentAccount);
            return currentAccount;
        }else{
            return null;
        }
    }

    @Override
    public SavingAccount createSavingAccount(Long accountRequestId) {
        Optional<AccountRequest> accountRequestOptional = accountRequestRepository.findById(accountRequestId);
        if(accountRequestOptional.isPresent()){
            AccountRequest accountRequest = accountRequestOptional.get();
            SavingAccount savingAccount = new SavingAccount(accountNumberGenerator.generateAccountNumber(),
                    accountRequest.getUser(),applicationTiming.getInstantDateTime(),BigDecimal.TEN,
                    accountRequest,accountRequest.getSavingAmount());
            savingAccountRepository.save(savingAccount);
            return savingAccount;
        }else{
            return null;
        }
    }

}

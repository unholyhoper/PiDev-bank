package tn.esprit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.bank.entity.BankAccount;
import tn.esprit.bank.entity.CurrentAccount;
import tn.esprit.bank.entity.SavingAccount;
import tn.esprit.bank.repository.BankAccountRepository;
import tn.esprit.bank.repository.CurrentAccountRepository;
import tn.esprit.bank.repository.SavingAccountRepository;

import java.util.List;

@Service
public class BankAccountService implements IBankAccountService {

    @Autowired
    CurrentAccountRepository currentAccountRepository;

    @Autowired
    SavingAccountRepository savingAccountRepository;


    @Autowired
    BankAccountRepository bankAccountRepository;


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

        /// TODO: test on presence before getting it
        return currentAccountRepository.findById(id).get();
    }

    @Override
    public SavingAccount findSavingAccountById(Long id) {
        /// TODO: test on presence before getting it
        return savingAccountRepository.findById(id).get();
    }

    @Override
    public CurrentAccount createCurrentAccount(Long accountRequestId) {
        /// TODO: create a bankAccount constructor from accountRequest
        return null;
    }

    @Override
    public SavingAccount createSavingAccount(Long accountRequestId) {
        /// TODO: create a bankAccount constructor from accountRequest
        return null;
    }

    @Override
    public void createBankAccount(BankAccount bankAccount) {
        if(bankAccount instanceof CurrentAccount){
            System.out.println("current");
        }else if (bankAccount instanceof SavingAccount){
            System.out.println("saving");
        }else{
            System.out.println("neither");
        }
    }
}

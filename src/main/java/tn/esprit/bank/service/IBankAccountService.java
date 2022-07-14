package tn.esprit.bank.service;



import tn.esprit.bank.entity.BankAccount;
import tn.esprit.bank.entity.CurrentAccount;
import tn.esprit.bank.entity.SavingAccount;

import java.util.List;

public interface IBankAccountService {

    List<BankAccount> findAllAccounts();
    List<CurrentAccount> findAllCurrentAccounts();
    List<SavingAccount> findAllSavingAccounts();
    CurrentAccount findCurrentAccountById(Long id);
    SavingAccount findSavingAccountById(Long id);
    CurrentAccount createCurrentAccount(Long accountRequestId);
    SavingAccount createSavingAccount(Long accountRequestId);



    void createBankAccount(BankAccount bankAccount);
}

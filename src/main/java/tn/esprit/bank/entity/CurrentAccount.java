package tn.esprit.bank.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.bank.enumeration.AccountType;

import javax.persistence.Entity;

@NoArgsConstructor
@Data
@Entity
public class CurrentAccount extends  BankAccount {


    public CurrentAccount(int accountNumber, AbstractUser user, AccountRequest accountRequest) {
        super(accountNumber, user, accountRequest);
    }
}

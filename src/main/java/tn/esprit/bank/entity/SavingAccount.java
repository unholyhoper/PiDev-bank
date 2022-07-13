package tn.esprit.bank.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class SavingAccount extends BankAccount{

    private int savingAmount;

    public SavingAccount(int accountNumber, AbstractUser user, AccountRequest accountRequest, int savingAmount) {
        super(accountNumber, user, accountRequest);
        this.savingAmount = savingAmount;
    }
}

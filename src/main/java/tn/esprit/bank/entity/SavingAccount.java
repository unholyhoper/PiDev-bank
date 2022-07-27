package tn.esprit.bank.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SavingAccount extends BankAccount{

    private int savingAmount;


    public SavingAccount(BigDecimal accountNumber, AbstractUser user, Date initialDate, float balance, AccountRequest accountRequest) {
        super(accountNumber, user, initialDate, balance, accountRequest);
    }
}

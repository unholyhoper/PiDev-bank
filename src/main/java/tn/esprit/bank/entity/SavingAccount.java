package tn.esprit.bank.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SavingAccount extends BankAccount{

    private float savingAmount;


    public SavingAccount(BigInteger accountNumber, AbstractUser user, Date initialDate, BigDecimal balance, AccountRequest accountRequest, float savingAmount) {
        super(accountNumber, user, initialDate, balance, accountRequest);
        this.savingAmount = savingAmount;
    }
}

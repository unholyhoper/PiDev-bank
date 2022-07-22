package tn.esprit.bank.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.bank.enumeration.AccountType;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CurrentAccount extends  BankAccount {
    private float interestRate;


    public CurrentAccount(BigInteger accountNumber, AbstractUser user, Date initialDate, BigDecimal balance, AccountRequest accountRequest, float interestRate) {
        super(accountNumber, user, initialDate, balance, accountRequest);
        this.interestRate = interestRate;
    }
}

package tn.esprit.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.bank.enumeration.AccountType;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class AccountRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private AccountType accountType;


    @ManyToOne
    private AbstractUser user;





    public AccountRequest(AccountType accountType) {
        this.accountType = accountType;
    }
}

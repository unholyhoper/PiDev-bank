package tn.esprit.bank.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import tn.esprit.bank.enumeration.AccountRequestStatus;
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

    private AccountRequestStatus status = AccountRequestStatus.CREATED;


    @ElementCollection(targetClass=String.class)
    private List<String> papersListUrls;


    private float interestRate;

    private float savingAmount;



    @ManyToOne
    private AbstractUser user;





    public AccountRequest(AccountType accountType,List<String> papersListUrls) {
        this.accountType = accountType;
        this.papersListUrls = papersListUrls;
    }
}

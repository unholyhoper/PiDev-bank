package tn.esprit.bank.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private Double amount;

    private String type;

    private String status;

    private Date date;


    @ManyToOne
    private BankAccount bankAccount;

}

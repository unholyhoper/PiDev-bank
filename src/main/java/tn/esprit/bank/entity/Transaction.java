package tn.esprit.bank.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.bank.enumeration.TransactionStatus;
import tn.esprit.bank.enumeration.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private BigDecimal amount;

    private TransactionType type;

    private TransactionStatus status;

    private Date date;


    @ManyToOne(cascade = CascadeType.ALL)
    private CurrentAccount bankAccountFrom;


    @ManyToOne(cascade = CascadeType.ALL)
    private CurrentAccount bankAccountTo;


}

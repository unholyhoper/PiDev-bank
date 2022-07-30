package tn.esprit.bank.vo;

import lombok.Data;
import tn.esprit.bank.enumeration.Periodicity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
public class TransactionPeriodicVO {

    private BigInteger accountNumberFrom;
    private BigInteger accountNumberTo;
    private BigDecimal amount;
    private Periodicity periodicity;
    private Date startDate;



}

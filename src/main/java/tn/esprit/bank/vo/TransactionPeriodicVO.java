package tn.esprit.bank.vo;

import lombok.Data;
import tn.esprit.bank.enumeration.Periodicity;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionPeriodicVO {

    private BigDecimal accountNumberFrom;
    private BigDecimal accountNumberTo;
    private Double amount;
    private Periodicity periodicity;
    private Date startDate;



}

package tn.esprit.bank.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionVO {

    private BigDecimal accountNumberFrom;
    private BigDecimal accountNumberTo;
    private BigDecimal amount;



}

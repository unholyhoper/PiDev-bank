package tn.esprit.bank.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class TransactionVO {

    private BigInteger accountNumberFrom;
    private BigInteger accountNumberTo;
    private BigDecimal amount;



}

package tn.esprit.bank.vo;

import lombok.Data;

@Data
public class ChargeRequestVO {


    private String description;
    private long amount;
    private long quantity;
    private String currency;
    private String stripeEmail;
    private String stripeToken;
    private String productName;

}

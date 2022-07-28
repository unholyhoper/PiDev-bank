package tn.esprit.bank.vo;

import lombok.Data;

@Data
public class PaiementCheckOutVO {



    private String id;
    private String payment_intent;
    private String payment_status;
    private String status;

    public PaiementCheckOutVO(String id, String payment_intent, String payment_status, String status){
        this.id=id;
        this.payment_intent=payment_intent;
        this.payment_status=payment_status;
        this.status=status;
    }

}

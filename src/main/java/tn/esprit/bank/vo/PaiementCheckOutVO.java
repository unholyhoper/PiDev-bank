package tn.esprit.bank.vo;

import lombok.Data;

@Data
public class PaiementCheckOutVO {



    private String id;
    private String paymentIntent;
    private String paymentStatus;
    private String status;

    private String clientReferenceId;

    public PaiementCheckOutVO(String id, String paymentIntent, String paymentStatus, String status, String clientReferenceId){
        this.id=id;
        this.paymentIntent=paymentIntent;
        this.paymentStatus=paymentStatus;
        this.status=status;
        this.clientReferenceId=clientReferenceId;
    }

}

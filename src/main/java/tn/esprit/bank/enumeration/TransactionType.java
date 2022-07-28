package tn.esprit.bank.enumeration;

import java.io.Serializable;

public enum TransactionType implements Serializable {


    VIREMENT("VIREMENT"),
    RETRAIT("RETRAIT"),
    VERSEMENT("VERSEMENT");
    private String type;

    TransactionType(String type) {

        this.type = type;
    }

    private String getType(){

        return type;
    }

    public Boolean isVirement(){

        return VIREMENT.getType().equals(type);
    }

    public Boolean isVersement(){

        return VERSEMENT.getType().equals(type);
    }

    public Boolean isRetrait(){

        return RETRAIT.getType().equals(type);
    }


}

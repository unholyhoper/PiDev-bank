package tn.esprit.bank.enumeration;

import java.io.Serializable;

public enum TransactionStatus implements Serializable {
    CREATED,
    PENDING,
    CANCELED,
    VALIDATED
}

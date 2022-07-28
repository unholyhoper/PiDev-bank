package tn.esprit.bank.enumeration;

import java.io.Serializable;

public enum LoanStatus implements Serializable {
    OPEN,
    IN_PROGRESS,
    ACCEPTED,
    CLOSED,
    REFUSED,
    WAITING
}

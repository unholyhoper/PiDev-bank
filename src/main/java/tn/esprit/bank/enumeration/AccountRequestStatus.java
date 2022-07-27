package tn.esprit.bank.enumeration;

import java.io.Serializable;

public enum AccountRequestStatus implements Serializable {
    CREATED,
    PENDING,
    CONFIRMED,
    VALIDATED,
    ACCEPTED,
    REOPENED,
    REJECTED
}

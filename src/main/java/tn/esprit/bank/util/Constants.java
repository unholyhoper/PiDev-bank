package tn.esprit.bank.util;

public interface Constants {
    String APP_ROOT = "/api";
    String USER = "USER";
    String GUEST = "GUEST";
    String BANKER = "BANKER";

    String ACCOUNT_SIGNING_MAIL_BODY ="Good Morning Mr ${client_name}\n\n" +
            "Your account request has been confirmed and here is down the signing date to validate it.\n" +
            "${signing_date} at 10am\n" +
            "\nBest Regards,\nTUNISIAN DIGITAL BANK";

    String ACCOUNT_SIGNING_MAIL_SUBJECT = "Tunisian Digital Bank : Signing Date";


}

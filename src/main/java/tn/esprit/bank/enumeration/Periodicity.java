package tn.esprit.bank.enumeration;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;

public enum Periodicity implements Serializable {

    DAILY(ChronoUnit.DAYS),
    WEEKLY(ChronoUnit.WEEKS),
    MONTHLY(ChronoUnit.MONTHS),
    YEARLY(ChronoUnit.YEARS);

    private TemporalUnit period;

    Periodicity( TemporalUnit period){

        this.period=period;
    }

    public TemporalUnit getPeriod() {
        return period;
    }
}

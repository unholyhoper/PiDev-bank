package tn.esprit.bank.util;


import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ApplicationTiming {

    public static  Calendar calendar = Calendar.getInstance();;

    public Date getInstantDateTime(){
       return calendar.getTime();
    }

}

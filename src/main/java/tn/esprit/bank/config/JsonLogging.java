package tn.esprit.bank.config;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JsonLogging {
    private static final Gson gson = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(JsonLogging.class);
    private JsonLogging(){}

    public static void printJson(Object obj){
        if(obj!=null) {
            logger.info(gson.toJson(obj));
        }
        }
}

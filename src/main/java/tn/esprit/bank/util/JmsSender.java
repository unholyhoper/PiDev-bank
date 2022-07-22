package tn.esprit.bank.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {


    @Autowired
    JmsTemplate jmsTemplate;

     public void  sendMessagePointToPoint(String message,String jmsQueueName){
         jmsTemplate.convertAndSend(jmsQueueName,message);
     }

}

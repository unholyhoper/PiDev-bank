package tn.esprit.bank.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.esprit.bank.entity.AccountRequest;
import tn.esprit.bank.enumeration.AccountRequestStatus;
import tn.esprit.bank.model.MailTemplate;
import tn.esprit.bank.repository.AccountRequestRepository;
import tn.esprit.bank.repository.CurrentAccountRepository;
import tn.esprit.bank.repository.SavingAccountRepository;
import tn.esprit.bank.util.Constants;
import tn.esprit.bank.util.JmsSender;


import java.sql.Date;
import java.util.List;

@Service
public class AccountRequestServiceImpl implements IAccountRequestService {

    @Autowired
    AccountRequestRepository accountRequestRepository;

    @Autowired
    CurrentAccountRepository currentAccountRepository;

    @Autowired
    SavingAccountRepository savingAccountRepository;


    @Autowired
    JmsSender jmsSender;



    @Value("${jms.mail.destination.queue}")
    String jmsMailQueue;

    @Override
    public AccountRequest getAccountRequestById(Long id) {
        if (accountRequestRepository.findById(id).isPresent()) {
            return accountRequestRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<AccountRequest> getAllAccountRequests() {
        return accountRequestRepository.findAll();
    }

    @Override
    public AccountRequest createAccountRequest(AccountRequest accountRequest) {

        //TODO: verify that this user don't have another account request, if it has reopen it.
        accountRequestRepository.save(accountRequest);
        return accountRequest;
    }

    @Override
    public String sendSigningRequest(Long accountRequestId, String signingDate) {

        AccountRequest accountRequest = accountRequestRepository.findById(accountRequestId).get();
        MailTemplate mailTemplate = new MailTemplate(accountRequest.getUser().getEmail(), Constants.ACCOUNT_SIGNING_MAIL_SUBJECT,
                "",Constants.ACCOUNT_SIGNING_MAIL_BODY.replace("${client_name}",
                accountRequest.getUser().getFirstName()+" "+accountRequest.getUser().getLastName()).replace(
                        "${signing_date}", signingDate.toString()));

        ObjectMapper objectMapper  = new ObjectMapper();
        try {
            jmsSender.sendMessagePointToPoint(objectMapper.writeValueAsString(mailTemplate),jmsMailQueue);
            return "sent";
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }

    }

    @Override
    public AccountRequest changeAccountRequestStatusById(Long id, AccountRequestStatus accountRequestStatus) {

        if (accountRequestRepository.findById(id).isPresent()) {
            AccountRequest accountRequest = accountRequestRepository.findById(id).get();
            accountRequest.setStatus(accountRequestStatus);
            accountRequestRepository.save(accountRequest);
            return accountRequest;
        } else {
            return null;
        }
    }

    @Override
    public int deleteAccountRequestById(Long id) {
        if (accountRequestRepository.findById(id).isPresent()) {
            if (savingAccountRepository.findSavingByAccountRequest(id.toString()) == null &&
                    currentAccountRepository.findCurrentByAccountRequest(id.toString()) == null) {
                accountRequestRepository.deleteById(id);
                return 0;
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    @Override
    public void patchAccountRequest(AccountRequest accountRequest) {
        accountRequestRepository.save(accountRequest);
    }
}

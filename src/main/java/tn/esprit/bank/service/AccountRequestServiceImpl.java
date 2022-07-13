package tn.esprit.bank.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.bank.entity.AccountRequest;
import tn.esprit.bank.repository.AccountRequestRepository;

@Service
public class AccountRequestServiceImpl implements IAccountRequestService {

    @Autowired
    AccountRequestRepository accountRequestRepository;

    @Override
    public AccountRequest createAccountRequest(AccountRequest accountRequest) {
        accountRequestRepository.save(accountRequest);
        return accountRequest;
    }

    @Override
    public void sendSigningRequest(AccountRequest accountRequest) {

    }

    @Override
    public void changeAccountRequestStatus(AccountRequest accountRequest) {

    }
}

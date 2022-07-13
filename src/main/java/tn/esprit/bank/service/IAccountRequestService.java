package tn.esprit.bank.service;

import tn.esprit.bank.entity.AccountRequest;

public interface IAccountRequestService {
     AccountRequest createAccountRequest(AccountRequest accountRequest);
     void sendSigningRequest(AccountRequest accountRequest);
     void changeAccountRequestStatus(AccountRequest accountRequest);
}

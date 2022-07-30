package tn.esprit.bank.service;

import tn.esprit.bank.entity.AccountRequest;
import tn.esprit.bank.enumeration.AccountRequestStatus;

import java.sql.Date;
import java.util.List;

public interface IAccountRequestService {
     AccountRequest getAccountRequestById(Long id);
     List<AccountRequest> getAllAccountRequests();
     AccountRequest createAccountRequest(AccountRequest accountRequest);
     String sendSigningRequest(Long accountRequestId, String signingDate);
     AccountRequest changeAccountRequestStatusById(Long id, AccountRequestStatus accountRequestStatus);
     int deleteAccountRequestById(Long id);
     void patchAccountRequest(AccountRequest accountRequest);
}

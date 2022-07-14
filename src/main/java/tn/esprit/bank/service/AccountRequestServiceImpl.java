package tn.esprit.bank.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.bank.entity.AccountRequest;
import tn.esprit.bank.enumeration.AccountRequestStatus;
import tn.esprit.bank.repository.AccountRequestRepository;
import tn.esprit.bank.repository.CurrentAccountRepository;
import tn.esprit.bank.repository.SavingAccountRepository;


import java.util.List;

@Service
public class AccountRequestServiceImpl implements IAccountRequestService {

    @Autowired
    AccountRequestRepository accountRequestRepository;

    @Autowired
    CurrentAccountRepository currentAccountRepository;

    @Autowired
    SavingAccountRepository savingAccountRepository;

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
        accountRequestRepository.save(accountRequest);
        return accountRequest;
    }

    @Override
    public String sendSigningRequest(AccountRequest accountRequest) {
        return null;
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

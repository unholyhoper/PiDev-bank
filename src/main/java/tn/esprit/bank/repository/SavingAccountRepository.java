package tn.esprit.bank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.SavingAccount;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {

    @Query("Select sav  from SavingAccount sav where account_request_id = ?1 ")
    SavingAccount findSavingByAccountRequest(String accountRequestId);
}

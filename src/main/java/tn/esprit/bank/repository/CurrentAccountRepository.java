package tn.esprit.bank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.CurrentAccount;


@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {

    @Query("SELECT curr FROM CurrentAccount curr WHERE account_request_id = ?1 ")
    CurrentAccount findCurrentByAccountRequest(String accountRequestId);
}

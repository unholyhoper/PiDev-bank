package tn.esprit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.AccountRequest;


@Repository
public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {
}

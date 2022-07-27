package tn.esprit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.bank.entity.TransactionPeriodic;
import tn.esprit.bank.enumeration.TransactionStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionPeriodicRepository extends JpaRepository<TransactionPeriodic, Long> {

   List<TransactionPeriodic> findAllByStatusAndNextDate(TransactionStatus status, LocalDate nextDate);



}

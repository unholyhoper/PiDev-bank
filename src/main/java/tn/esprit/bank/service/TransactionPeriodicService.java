package tn.esprit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.bank.entity.BankAccount;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.entity.TransactionPeriodic;
import tn.esprit.bank.enumeration.Periodicity;
import tn.esprit.bank.enumeration.TransactionStatus;
import tn.esprit.bank.enumeration.TransactionType;
import tn.esprit.bank.repository.BankAccountRepository;
import tn.esprit.bank.repository.TransactionPeriodicRepository;
import tn.esprit.bank.repository.TransactionRepository;
import tn.esprit.bank.vo.TransactionPeriodicVO;
import tn.esprit.bank.vo.TransactionVO;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionPeriodicService implements ITransactionPeriodicService {

    @Autowired
    TransactionPeriodicRepository transactionPeriodicRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    TransactionPeriodicService transactionService;




    public TransactionPeriodic createPeriodicTransaction(TransactionPeriodicVO transactionPeriodicVO) {
        checkTransaction(transactionPeriodicVO);
        BankAccount bankAccountFrom = bankAccountRepository.findBankAccountByAccountNumber(transactionPeriodicVO.getAccountNumberFrom()).orElseThrow(() ->
                new RuntimeException("There is no Bank account found with ID = " + transactionPeriodicVO.getAccountNumberFrom())
        );

        BankAccount bankAccountTo = bankAccountRepository.findBankAccountByAccountNumber(transactionPeriodicVO.getAccountNumberTo()).orElseThrow(() ->
                new RuntimeException("There is no Bank Account found with ID = " + transactionPeriodicVO.getAccountNumberTo())
        );
        TransactionPeriodic transaction = new TransactionPeriodic();
        transaction.setStatus(TransactionStatus.CREATED);
        transaction.setAmount(transactionPeriodicVO.getAmount());
        transaction.setPeriodicity(transactionPeriodicVO.getPeriodicity());
        LocalDate startDate = Instant.ofEpochMilli(transactionPeriodicVO.getStartDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        transaction.setStartDate(startDate);
        transaction.setNextDate(startDate);
        transaction.setBankAccountFrom(bankAccountFrom);
        transaction.setBankAccountTo(bankAccountTo);
        return transactionPeriodicRepository.save(transaction);

    }


    private void checkTransaction(TransactionPeriodicVO transactionPeriodicVO) {

        if (transactionPeriodicVO == null) {
            throw new RuntimeException("Transaction object should not be null");
        }
        if (transactionPeriodicVO.getPeriodicity() == null) {
            throw new RuntimeException("Transaction Periodicity should not be null");
        }

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        LocalDate startDate = Instant.ofEpochMilli(transactionPeriodicVO.getStartDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        if (startDate == null || startDate.isBefore(tomorrow) ) {
            System.out.println("tomorrow="+tomorrow);
            throw new RuntimeException("Transaction start date should not be null and should at least start tomorrow. start date = "+ transactionPeriodicVO.getStartDate());
        }
        if ( transactionPeriodicVO.getAccountNumberFrom() == null) {

            throw new RuntimeException("Account number from should not be null");
        }
        if (transactionPeriodicVO.getAccountNumberTo() == null) {
            throw new RuntimeException("Account number To should not be null");
        }
        if (transactionPeriodicVO.getAmount() == null || transactionPeriodicVO.getAmount() == 0) {
            throw new RuntimeException("the amount to transfer should be > 0");
        }
    }


    public TransactionPeriodic cancelTransactionPeriodic(Long transactionId) {
        TransactionPeriodic transaction = transactionPeriodicRepository.findById(transactionId).orElseThrow(() ->
                new RuntimeException("There is no Transaction found with ID = " + transactionId)
        );
        transaction.setStatus(TransactionStatus.CANCELED);
        return transactionPeriodicRepository.save(transaction);

    }


    }




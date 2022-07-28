package tn.esprit.bank.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.entity.TransactionPeriodic;
import tn.esprit.bank.enumeration.TransactionStatus;
import tn.esprit.bank.enumeration.TransactionType;
import tn.esprit.bank.repository.TransactionPeriodicRepository;
import tn.esprit.bank.repository.TransactionRepository;
import tn.esprit.bank.service.TransactionService;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TransactionPeriodicBatch {

    @Autowired
    TransactionPeriodicRepository transactionPeriodicRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionRepository transactionRepository;



    @Scheduled(cron = "0 14 23 * * *")
    public void  createTransactionPeriodic() {

        List<TransactionPeriodic> listeTransactionPeriodic = transactionPeriodicRepository.findAllByStatusAndNextDate( TransactionStatus.CREATED, LocalDate.now());
        List<Transaction> transactions = listeTransactionPeriodic.parallelStream()
                .map(this::proccessTransactionPeriodic)
                .collect(Collectors.toList());

        transactionRepository.saveAll(transactions);
        transactionPeriodicRepository.saveAll(listeTransactionPeriodic);


    }

    private Transaction proccessTransactionPeriodic(TransactionPeriodic transactionPeriodic){
        Transaction transaction = transactionService.createTransaction(TransactionType.VIREMENT,
                transactionPeriodic.getBankAccountFrom(),
                transactionPeriodic.getBankAccountTo(),
                transactionPeriodic.getAmount());


        TemporalUnit period = transactionPeriodic.getPeriodicity().getPeriod();

        transactionPeriodic.setNextDate(transactionPeriodic.getNextDate().plus(1, period));
        return transaction;

}}

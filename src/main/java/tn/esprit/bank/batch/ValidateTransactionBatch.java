package tn.esprit.bank.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.bank.config.JsonLogging;
import tn.esprit.bank.entity.BankAccount;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.enumeration.TransactionStatus;
import tn.esprit.bank.repository.BankAccountRepository;
import tn.esprit.bank.repository.TransactionRepository;
import tn.esprit.bank.service.TransactionService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidateTransactionBatch {

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionRepository transactionRepository;

   ;

    private static final Logger logger = LoggerFactory.getLogger(ValidateTransactionBatch.class);


    @Scheduled(cron = "0 * * * * *")
    public void validateTransaction() {

        logger.info("Start Validate Transaction Batch");
        List<Transaction> listeTransaction = transactionRepository.findAllByStatus(TransactionStatus.PENDING);
        List<Transaction> listTransactionValidated = listeTransaction.parallelStream().peek(transaction ->
                {
                    BankAccount bankAccountFrom = transaction.getBankAccountFrom();
                    BankAccount bankAccountTo = transaction.getBankAccountTo();
                    if (bankAccountFrom != null) {

                        bankAccountFrom.setBalance(bankAccountFrom.getBalance().subtract(transaction.getAmount()));
                    }
                    if (bankAccountTo != null) {
                        bankAccountTo.setBalance(bankAccountTo.getBalance().add(transaction.getAmount()));

                    }
                    transaction.setStatus(TransactionStatus.VALIDATED);
                    transactionRepository.save(transaction);

                }
        ).collect(Collectors.toList());
        logger.info("End Validate Transaction Batch");



    }
}

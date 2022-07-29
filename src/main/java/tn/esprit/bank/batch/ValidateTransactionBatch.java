package tn.esprit.bank.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.bank.entity.BankAccount;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.enumeration.TransactionStatus;
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

    @Scheduled(cron = "0 31 2 * * *")
    public void validateTransaction() {
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


    }
}

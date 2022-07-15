package tn.esprit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.bank.entity.BankAccount;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.enumeration.TransactionStatus;
import tn.esprit.bank.repository.BankAccountRepository;
import tn.esprit.bank.repository.TransactionRepository;
import tn.esprit.bank.vo.TransactionVO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;


    @Override
    public Transaction createTransaction(TransactionVO transactionVO) {

        BankAccount bankAccountFrom = bankAccountRepository.findById(transactionVO.getAccountNumberFrom().longValue()).orElseThrow(() ->
                new RuntimeException("There is no Transaction found with ID = " + transactionVO.getAccountNumberFrom())
        );

        BankAccount bankAccountTo = bankAccountRepository.findById(transactionVO.getAccountNumberTo().longValue()).orElseThrow(() ->
                new RuntimeException("There is no Transaction found with ID = " + transactionVO.getAccountNumberTo())
        );

        Transaction transaction = new Transaction();
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setAmount(transactionVO.getAmount());
        transaction.setDate(new Date());
        transaction.setBankAccountFrom(bankAccountFrom);
        transaction.setBankAccountTo(bankAccountTo);

        return transactionRepository.save(transaction);


    }


    @Override
    public List<Transaction> findAllTransactions() {

        return transactionRepository.findAll();

    }

    @Override
    public Optional<Transaction> findTransactionById(Long transactionId) {

        return transactionRepository.findById(transactionId);

    }


    public Transaction updateTransaction(Long transactionId, String status) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() ->
                new RuntimeException("There is no Transaction found with ID = " + transactionId)
        );
        transaction.setStatus(TransactionStatus.valueOf(status));
        return transactionRepository.save(transaction);


    }
}

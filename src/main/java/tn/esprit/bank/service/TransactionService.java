package tn.esprit.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.bank.entity.BankAccount;
import tn.esprit.bank.entity.CurrentAccount;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.enumeration.TransactionStatus;
import tn.esprit.bank.enumeration.TransactionType;
import tn.esprit.bank.repository.BankAccountRepository;
import tn.esprit.bank.repository.CurrentAccountRepository;
import tn.esprit.bank.repository.TransactionRepository;
import tn.esprit.bank.vo.TransactionVO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CurrentAccountRepository bankAccountRepository;

    @Autowired
    TransactionService transactionService;

    //Creation et persistance de la transaction
    public Transaction createTransaction(TransactionType transactionType, CurrentAccount bankAccountFrom, CurrentAccount bankAccountTo, BigDecimal amount) {

        Transaction transaction = new Transaction();
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setType(transactionType);
        transaction.setAmount(amount);
        transaction.setDate(new Date());
        transaction.setBankAccountFrom(bankAccountFrom);
        transaction.setBankAccountTo(bankAccountTo);
        return transactionRepository.save(transaction);

    }
    //Vérification des Param d'entrée de la transactionVO
    private void checkTransaction(TransactionVO transactionVO, TransactionType transactionType) {

        if (transactionVO == null) {
            throw new RuntimeException("Transaction object should not be null");
        }
        if ((transactionType.isVirement() || transactionType.isRetrait()) && transactionVO.getAccountNumberFrom() == null) {

            throw new RuntimeException("Account number from should not be null");
        }
        if ((transactionType.isVirement() || transactionType.isVersement()) && transactionVO.getAccountNumberTo() == null) {
            throw new RuntimeException("Account number To should not be null");
        }
        if (transactionVO.getAmount() == null || transactionVO.getAmount().equals(BigDecimal.ZERO)) {
            throw new RuntimeException("the amount to transfer should be > 0");
        }
    }
    //Vérification et creation de la transaction Virement
    @Override
    public Transaction createTransactionVirement(TransactionVO transactionVO) {

        checkTransaction(transactionVO, TransactionType.VIREMENT);
        CurrentAccount bankAccountFrom = bankAccountRepository.findCurrentAccountByAccountNumber(transactionVO.getAccountNumberFrom()).orElseThrow(() ->
                new RuntimeException("There is no Bank account found with ID = " + transactionVO.getAccountNumberFrom())
        );

        CurrentAccount bankAccountTo = bankAccountRepository.findCurrentAccountByAccountNumber(transactionVO.getAccountNumberTo()).orElseThrow(() ->
                new RuntimeException("There is no Bank Account found with ID = " + transactionVO.getAccountNumberTo())
        );

        return createTransaction(TransactionType.VIREMENT, bankAccountFrom, bankAccountTo,transactionVO.getAmount());

    }
    //Vérification et creation de la transaction Versement
    @Override
    public Transaction createTransactionVersement(TransactionVO transactionVO) {


        checkTransaction(transactionVO, TransactionType.VERSEMENT);

        CurrentAccount bankAccountTo = bankAccountRepository.findCurrentAccountByAccountNumber(transactionVO.getAccountNumberTo()).orElseThrow(() ->
                new RuntimeException("There is no Bank Account found with ID = " + transactionVO.getAccountNumberTo())
        );

        return createTransaction( TransactionType.VERSEMENT, null, bankAccountTo, transactionVO.getAmount());


    }

    //Vérification et creation de la transaction Retrait
    @Override
    public Transaction createTransactionRetrait(TransactionVO transactionVO) {


        checkTransaction(transactionVO, TransactionType.RETRAIT);

        CurrentAccount bankAccountFrom = bankAccountRepository.findCurrentAccountByAccountNumber(transactionVO.getAccountNumberFrom()).orElseThrow(() ->
                new RuntimeException("There is no Bank account found with ID = " + transactionVO.getAccountNumberFrom())
        );

        return createTransaction( TransactionType.RETRAIT, bankAccountFrom, null,transactionVO.getAmount());


    }


    @Override
    public List<Transaction> findAllTransactions() {

        return transactionRepository.findAll();

    }

    @Override
    public Transaction findTransactionById(Long transactionId) {

        return transactionRepository.findById(transactionId).orElseThrow(() ->
                new RuntimeException("There is no Transaction found with ID = " + transactionId)
        );

    }
    //Mise a jour de la status de la transaction
    public Transaction updateTransaction(Long transactionId, String status) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() ->
                new RuntimeException("There is no Transaction found with ID = " + transactionId)
        );
        transaction.setStatus(TransactionStatus.valueOf(status));
        return transactionRepository.save(transaction);

    }






    }



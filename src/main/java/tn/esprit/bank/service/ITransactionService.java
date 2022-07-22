package tn.esprit.bank.service;

import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.vo.TransactionVO;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {

    List<Transaction> findAllTransactions() ;

    Transaction createTransaction(TransactionVO transactionVO) ;


    Optional<Transaction> findTransactionById(Long transactionId);
}

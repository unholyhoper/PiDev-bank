package tn.esprit.bank.service;

import tn.esprit.bank.entity.BankAccount;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.entity.TransactionPeriodic;
import tn.esprit.bank.enumeration.Periodicity;
import tn.esprit.bank.vo.TransactionPeriodicVO;
import tn.esprit.bank.vo.TransactionVO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITransactionPeriodicService {


    TransactionPeriodic createPeriodicTransaction(TransactionPeriodicVO transactionPeriodicVO) ;




}

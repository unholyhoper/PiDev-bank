package tn.esprit.bank.batch;

import com.stripe.model.PaymentIntent;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.bank.entity.BankAccount;
import tn.esprit.bank.entity.CurrentAccount;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.enumeration.TransactionStatus;
import tn.esprit.bank.enumeration.TransactionType;
import tn.esprit.bank.repository.BankAccountRepository;
import tn.esprit.bank.repository.CurrentAccountRepository;
import tn.esprit.bank.vo.PaiementCheckOutVO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

public class PaymentItemProcessor implements ItemProcessor<PaiementCheckOutVO, Transaction> {

    @Autowired
    CurrentAccountRepository bankAccountRepository;
    @Override
    public Transaction process(final PaiementCheckOutVO paiementCheckOutVO) throws Exception {

        final String paymentIntentId = paiementCheckOutVO.getPaymentIntent();
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);


        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(paymentIntent.getAmount()/100));
        transaction.setType(TransactionType.PAYMENT);
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setDate(new Date());
        Optional<CurrentAccount> bankAccount = bankAccountRepository.findCurrentAccountByAccountNumber(BigInteger.valueOf(Long.parseLong(paiementCheckOutVO.getClientReferenceId())));
        transaction.setBankAccountFrom(bankAccount.orElseThrow());
        return transaction;
    }

}

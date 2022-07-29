package tn.esprit.bank.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.bank.config.JsonLogging;
import tn.esprit.bank.entity.CurrentAccount;
import tn.esprit.bank.entity.Loan;
import tn.esprit.bank.entity.LoanTranches;
import tn.esprit.bank.exception.RessourceNotFoundException;
import tn.esprit.bank.repository.CurrentAccountRepository;
import tn.esprit.bank.repository.LoanRepository;
import tn.esprit.bank.repository.LoanTranchesRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static tn.esprit.bank.enumeration.LoanStatus.*;

@Service
public class LoanServiceImpl implements ILoanService {
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    LoanTranchesRepository loanTranchesRepository;
    @Autowired
    CurrentAccountRepository currentAccountRepository;
    @Override
    public Loan createLoanRequest(Loan loan) throws Exception {
        Double accountBalance =null;
        if(loan.getDuration()<1 || loan.getDuration()>60){
            throw new Exception("Duration should be by month");
        }
        CurrentAccount currentAccount = currentAccountRepository.findById(loan.getBankAccount().getId())
                .orElseThrow(() -> new RuntimeException("There is no Account found with ID = " + loan.getBankAccount().getId()));
        try {
            accountBalance =Double.parseDouble(String.valueOf(currentAccount.getBalance()));

        }catch (Exception e){
            throw new Exception("Please terminate your account information we need your account balance to verify the loan");

        }
        Double loanAmount = loan.getAmount();
        String interest =loan.getInterest().substring(0, loan.getInterest().length() - 1);
        Double interestPercent = Double.parseDouble(interest);
        Double loanAmountWithInterest = ((loanAmount*interestPercent)/100)+loanAmount;
        Double loanPaymentNeeded = loanAmountWithInterest/loan.getDuration();
        Double payment = Double.parseDouble(loan.getPayment());
        Double poucentage = (accountBalance*40)/100;
        if(loanPaymentNeeded>poucentage){
            throw new Exception("you can't have this loan the max amount you can pay per month to our bank is : "+poucentage +" for the duration you indicated you should pay : "+ loanPaymentNeeded + " so please modify the duration or the loan amount" );
        }
        JsonLogging.printJson(poucentage);
        if(payment>poucentage){
            throw new Exception("you can't have this loan the max amount you can pay to our bank is : "+poucentage);
        }
            loanRepository.save(loan);
            for(int i =0 ; i<loan.getDuration() ;i++) {
                System.out.println(i);
                LocalDate newDate;
                LoanTranches loanTranches = new LoanTranches();
                loanTranches.setLoan(loan);
                loanTranches.setAmount(loan.getPayment());
                LocalDate date = LocalDate.now();
                if(i==0){
                    newDate=date;
                }else {
                    newDate = date.plusMonths(i);
                }
                loanTranches.setDate(newDate.toString());
                loanTranchesRepository.save(loanTranches);
                JsonLogging.printJson(loanTranches);
            }
            return loan;
    }

    @Override
    public List<Loan> getAllLoansRequest() {
        return loanRepository.findAll();
    }

    @Override
    public List<Loan> getAllLoanByIdRequest(Long id) {
        List<Loan> loanList = new ArrayList<>();
        loanList.add(loanRepository.findById(id).get());
        return loanList ;
    }

    @Override
    public void deleteLoanByIdRequest(Long id) {
        try {
            List<LoanTranches> loanTranchesList = new ArrayList<>();
            loanTranchesList.addAll(loanTranchesRepository.findAllByLoanId(id));
            JsonLogging.printJson(loanTranchesList);
            for(int i =0; i<loanTranchesList.size();i++){
                loanTranchesRepository.deleteById(loanTranchesList.get(i).getId());
            }
            loanRepository.deleteById(id);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public Loan updateLoanRequest(Long id, Loan newLoan) {
        Loan updateLoan = loanRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("loan not found" + id));
        updateLoan.setDuration(newLoan.getDuration() != null ? newLoan.getDuration() : updateLoan.getDuration());
        updateLoan.setAmount(newLoan.getAmount() != null ? newLoan.getAmount() : updateLoan.getAmount());
        updateLoan.setInterest(newLoan.getInterest() != null ? newLoan.getInterest() : updateLoan.getInterest());
        updateLoan.setPayment(newLoan.getPayment() != null ? newLoan.getPayment() : updateLoan.getPayment());
        updateLoan.setLoanStatus(newLoan.getLoanStatus() != null ? newLoan.getLoanStatus() : updateLoan.getLoanStatus());
        updateLoan.setBankAccount(newLoan.getBankAccount() != null ? newLoan.getBankAccount() : updateLoan.getBankAccount());
        return loanRepository.save(updateLoan);

    }

    @Override
    //each 1min the day.
    @Scheduled(cron = "0 * * * * *")
    public void updateLoanTranchStatus() {
        LocalDate date = LocalDate.now();
        List<LoanTranches> loanTranchesList = new ArrayList<>();
        loanTranchesList.addAll(loanTranchesRepository.findAllByLoanTranchDate(date.toString()));
        JsonLogging.printJson(loanTranchesList);
        LoanTranches loanTranches;
        for(int i=0 ; i< loanTranchesList.size();i++){
            loanTranches= loanTranchesList.get(i);
            loanTranches.setLoanStatus(CLOSED);
            loanTranchesRepository.save(loanTranches);
        }
    }

    @Override
    public List<Loan> getLoansByAccountId(Long accountId) {
        List<Loan> allLoans = new ArrayList<>();
        List<Loan> accountLoans = new ArrayList<>();
        allLoans.addAll(getAllLoansRequest());
        for (int i=0;i<allLoans.size();i++){
            if(allLoans.get(i).getBankAccount().getId()==accountId){
                accountLoans.add(allLoans.get(i));
            }
        }
        return accountLoans;
    }

    @Override
    public void export(HttpServletResponse response, Long id) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Your Loan Info", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        List<Loan> loanList = new ArrayList<>();
        loanList.addAll(getAllLoanByIdRequest(id));

        Paragraph section1Title = new Paragraph("Loan Info :", fontTitle);
        section1Title.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph2 = new Paragraph("\n \r Loan id : " + loanList.get(0).getId() +" \n Account Number : " + loanList.get(0).getBankAccount().getAccountNumber()+ " \n Loan Amount : "+ loanList.get(0).getAmount()+" \n Loan Interest : " + loanList.get(0).getInterest() , fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        List<LoanTranches> loanTranchesList = new ArrayList<>();
        loanTranchesList.addAll(loanTranchesRepository.findAllByLoanId(id));
        JsonLogging.printJson(loanTranchesList);

        document.add(paragraph);
        document.add(section1Title);
        document.add(paragraph2);

        Paragraph section2Title = new Paragraph(" \n \r Loan tranches Info : ", fontTitle);
        section2Title.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(section2Title);
        for(int i=0; i<loanTranchesList.size();i++){
            Paragraph paragraph3 = new Paragraph("\n \r Tranch Date : "+loanTranchesList.get(i).getDate()+" \n Tranch Amount : " + loanTranchesList.get(0).getAmount()+" \n Tranch Status : "+ loanTranchesList.get(i).getLoanStatus() , fontParagraph);
            paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph3);
        }

        document.close();
    }


}

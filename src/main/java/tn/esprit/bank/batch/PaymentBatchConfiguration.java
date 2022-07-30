package tn.esprit.bank.batch;

import com.stripe.exception.StripeException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.bank.controller.PaymentController;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.repository.TransactionRepository;
import tn.esprit.bank.vo.PaiementCheckOutVO;

import java.util.stream.Collectors;

@ComponentScan
@Configuration
@EnableBatchProcessing
public class PaymentBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;



    @Autowired
    public PaymentController paymentController;

    @Bean
    public ItemReader<PaiementCheckOutVO> reader() throws StripeException {

        return new ListItemReader<>(paymentController.listCheckout().stream().filter(paiementCheckOutVO ->
                paiementCheckOutVO.getPaymentStatus().equals("paid") && paiementCheckOutVO.getClientReferenceId()!=null).collect(Collectors.toList()));
    }

    @Bean
    public ItemProcessor<PaiementCheckOutVO, Transaction> processor() {
        return new PaymentItemProcessor();
    }

    @Bean
    public RepositoryItemWriter<Transaction> writer(TransactionRepository transactionRepository) {

        RepositoryItemWriter<Transaction> repositoryItemWriter = new RepositoryItemWriter<>();
        repositoryItemWriter.setRepository(transactionRepository);
        repositoryItemWriter.setMethodName("save");

        return repositoryItemWriter;

    }

    @Bean
    public Job importPaymentJob(Step step1) {
        return jobBuilderFactory.get("importPaymentJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(RepositoryItemWriter<Transaction> writer) throws StripeException {
        return stepBuilderFactory.get("step1")
                .<PaiementCheckOutVO, Transaction>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }


}

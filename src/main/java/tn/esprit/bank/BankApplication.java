package tn.esprit.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tn.esprit.bank.repository.AccountRequestRepository;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext context =  SpringApplication.run(BankApplication.class, args);

		AccountRequestRepository accountRequestRepository = context.getBean(AccountRequestRepository.class);

		System.out.println(accountRequestRepository.findAll());



	}

}

package tn.esprit.bank.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.bank.enumeration.AccountType;
@Data
@Entity
@NoArgsConstructor

public class Question {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long Id;
	 
	 
	 private String title ;


	 @ManyToOne
	 private AbstractUser user;


	 @OneToOne
	 private Answer answer;
}

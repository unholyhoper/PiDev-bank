package tn.esprit.bank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.bank.enumeration.AccountType;
@Data
@Entity
@NoArgsConstructor

public class Answer {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long Id;
	 
	 private String contient ;

}

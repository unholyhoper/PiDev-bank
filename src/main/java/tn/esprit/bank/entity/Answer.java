package tn.esprit.bank.entity;

import javax.persistence.*;

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


	 @ManyToOne
	 private AbstractUser user;
	 
	 @OneToOne
	 @JoinColumn(name="fk_question_id")
	 private Question question;


	
}

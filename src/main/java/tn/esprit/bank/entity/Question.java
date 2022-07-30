package tn.esprit.bank.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.bank.enumeration.AccountType;
import tn.esprit.bank.enumeration.QuestionStatus;
@Data
@Entity
@NoArgsConstructor

public class Question {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long Id;
	 
	 
	 private String title ;

	 private String description ;
	private String sentiment;

	 @Column(name = "status")
	    @Enumerated(EnumType.STRING)
	    private QuestionStatus status;
	 
   @OneToOne(mappedBy = "question",cascade =  CascadeType.ALL)
   @JsonIgnore
   private Answer answer ;


	 @ManyToOne
	 private AbstractUser user;


}

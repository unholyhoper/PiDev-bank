package tn.esprit.bank.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.Answer;
import tn.esprit.bank.service.AnswerServiceImpl;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
	@Autowired
	 AnswerServiceImpl answerServiceImpl;

	 

	 @GetMapping("/getAllAnswer")
	    public List<Answer> getAllAnswers(){
	        return  answerServiceImpl.getAllAnswer();
	    }
	    
	    @GetMapping("/getAnswer")
	    public Answer getAnswertById(@RequestParam Long id){
	        return  answerServiceImpl.findAnswerById(id);
	    }
	    
	    
	    @PostMapping("/createAnswer")
	    public Answer createAnswer(@RequestBody Answer answer ){
	  //  answer.setUser((AbstractUser)principal);

	    	 
	    	 
	         return answerServiceImpl.createAnswer(answer);
	     
	    }
	    
	    @DeleteMapping("deleteAnswerById")
	    public ResponseEntity<String> deleteAnswerById( Long id){
	    	 try {
	    		 answerServiceImpl.deleteAnswerById(id);
	         return ResponseEntity.ok("Answer  "+id+"  deleted");
	    }
	    catch (Exception e){
	         ResponseEntity.badRequest().build();
	         return ResponseEntity.badRequest().build();
	     }

          }


	    
}

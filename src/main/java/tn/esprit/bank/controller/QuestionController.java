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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.bank.entity.AbstractUser;
import tn.esprit.bank.entity.Question;
import tn.esprit.bank.entity.Transaction;
import tn.esprit.bank.service.QuestionServiceImpl;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
	 @Autowired
	 QuestionServiceImpl questionServiceImpl;
	 
	 
	 @GetMapping("/pdf/generate")
	    public void generatePDF(HttpServletResponse response) throws IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());

	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
           // List<Question> ls = questionServiceImpl.getAllQuestion();
	        List<Question> ls =questionServiceImpl.getAllQuestion();
	        this.questionServiceImpl.export(ls,response);
	    }
	 


	    @GetMapping("/getAllQuestion")
	    public List<Question> getAllQuestion(){
	        return  questionServiceImpl.getAllQuestion();
	    }
	    
	    @GetMapping("/getQuestion")
	    public Question getQuestionById(@RequestParam Long id){
	        return  questionServiceImpl.findQuestionById(id);
	    }
	    
	    
	    
	    @PostMapping("/createQuestion")
	    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
	     try {
	    	 //question.setUser(user);
	    	 questionServiceImpl.createQuestion(question);
	         return ResponseEntity.ok(question);
	     }catch (Exception e){
	         ResponseEntity.badRequest().build();
	         return ResponseEntity.badRequest().build();
	     }
	    }
	    

	    
	    @PutMapping("/updateQuestion/{questionId}/{status}")
	    public Question updateQuestion(@PathVariable("questionId") Long questionId, @PathVariable("status") String status) {


	        return questionServiceImpl.updateQuestion(questionId, status);

	    }
	    
	    
	    @DeleteMapping("deleteQuestionById")
	    public ResponseEntity<String> deleteQuestionById( Long id){
	    	 try {
	    	questionServiceImpl.deleteQuestionById(id);
	         return ResponseEntity.ok("Question  "+id+"  deleted");
	    }
	    catch (Exception e){
	         ResponseEntity.badRequest().build();
	         return ResponseEntity.badRequest().build();
	     }

          }
	    

}

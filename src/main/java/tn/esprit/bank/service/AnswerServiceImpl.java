package tn.esprit.bank.service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.dialect.FrontBaseDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import tn.esprit.bank.entity.Answer;
import tn.esprit.bank.entity.Question;
import tn.esprit.bank.repository.AnswerRepository;
@Service

public class AnswerServiceImpl implements IAnswerService{
	 @Autowired
	   AnswerRepository answerRepository;
	// @Autowired
	// Principal principal;
	// @Autowired
	// UserServiceImpl userservice;
	 
	
	@Override

	public Answer findAnswerById(Long id) {
		// TODO Auto-generated method stub
		 if (answerRepository.findById(id).isPresent()) {
	            return answerRepository.findById(id).get();
	        } else {
	            return null;
	        }		}

	@Override
	public List<Answer> getAllAnswer() {
		// TODO Auto-generated method stub
		return answerRepository.findAll();
	}

	@Override
	public Answer createAnswer(Answer answer ) {
		// TODO Auto-generated method stub
	//	answer.setUser(userservice.loadUserByUsername(principal.getName()));
        return 		answerRepository.save(answer);

	}

	@Override
	public void deleteAnswerById(Long id) {
		// TODO Auto-generated method stub
		answerRepository.deleteById(id);

	}

}

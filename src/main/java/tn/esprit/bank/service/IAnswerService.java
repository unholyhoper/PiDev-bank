package tn.esprit.bank.service;

import java.util.List;

import tn.esprit.bank.entity.Answer;
import tn.esprit.bank.entity.Question;

public interface IAnswerService {
	Answer findAnswerById(Long id);
	  List<Answer> getAllAnswer();
	  Answer createAnswer(Answer answer);
	  void deleteAnswerById(Long id);
}

package tn.esprit.bank.service;

import java.util.List;


import tn.esprit.bank.entity.Question;

public interface IQuestionService {
	  Question findQuestionById(Long id);
	  List<Question> getAllQuestion();
	  Question createQuestion(Question question);
	  void deleteQuestionById(Long id);
Question updateQuestion (Long questionId, String status);
}

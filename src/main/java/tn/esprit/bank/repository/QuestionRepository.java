package tn.esprit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.bank.entity.Question;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	@Query(value="SELECT count(*) FROM question WHERE question.status='CREATED'or question.status='PENDING' or question.status='CONFIRMED' or question.status='VALIDATED' or question.status='ACCEPTED' " , nativeQuery=true)
    int getcountrecs();
	
	
	@Query(value="SELECT * FROM question WHERE question.status='PENDING'" , nativeQuery=true)
    List<Question> getQuestionPend()  ;

}

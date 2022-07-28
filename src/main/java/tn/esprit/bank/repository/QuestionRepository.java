package tn.esprit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.bank.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}

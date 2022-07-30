package tn.esprit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.bank.entity.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}

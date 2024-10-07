package com.quiz.quiz_service.repo;

import com.quiz.quiz_service.model.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizzRepo extends JpaRepository<Quizz, Long> {
    Quizz getQuizzById(Long id);
}

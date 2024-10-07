package com.question.question_service.repo;


import com.question.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.question_type = :type")
    List<Question> findByType(@Param("type") String type);

    Question getQuestionById(Long id);
}


package com.quiz.quiz_service.service;
import com.quiz.quiz_service.model.Answer;
import com.quiz.quiz_service.model.QuestionWrapper;
import com.quiz.quiz_service.model.Quizz;
import com.quiz.quiz_service.repo.QuizzRepo;
import com.quiz.quiz_service.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizzService {
    @Autowired
    QuizzRepo quizzRepo;

    @Autowired
    QuizInterface quizInterface;


    public Quizz createQuizzByType(String quizzName, String type) {
        Quizz quizz = new Quizz();
        quizz.setQuizzName(quizzName);

        ResponseEntity<List<Integer>> quizCreated = quizInterface.generateQuestion(type);
        if (quizCreated.getStatusCode().is2xxSuccessful()) {
            quizz.setQuestions(quizCreated.getBody());
        }
        System.out.println(quizz);
        return quizzRepo.save(quizz);
    }

    public List<QuestionWrapper> getQuizzById(Long id) {
        Quizz quizz = quizzRepo.getQuizzById(id);
        List<Integer> quizzIds = quizz.getQuestions();
        ResponseEntity<List<QuestionWrapper>> questionWrapper = quizInterface.getQuestionsByIds(quizzIds);
        return questionWrapper.getBody();
    }

    public int calculateScore( List<Answer> answers) {
        return quizInterface.calculate(answers);
    }
}

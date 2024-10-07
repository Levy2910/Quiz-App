package com.question.question_service.service;

import com.question.question_service.model.Answer;
import com.question.question_service.model.Question;
import com.question.question_service.model.QuestionWrapper;
import com.question.question_service.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    
    @Autowired
    QuestionRepo questionRepo;

    public List<Question> getQuestions() {
        try {
            return questionRepo.findAll(); // Fetch all questions
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch questions", e); // Rethrow or handle appropriately
        }
    }

    public Question addQuestion(Question question) {
        try {
            return questionRepo.save(question);
        }catch (Exception e) {
            throw new RuntimeException("Unable to save question", e);
        }
    }
    public List<Question> addQuestions(List<Question> questions) {
        try{
            questionRepo.saveAll(questions);
        }catch (Exception e) {
            throw new RuntimeException("Unable to save questions", e);
        }
        return questions;
    }

    public Question getQuestionById(Long id) {
        return questionRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Question not found with ID: " + id));
    }

    public void deleteQuestionById(Long id) {
        if (!questionRepo.existsById(id)) {
            throw new RuntimeException("Question not found with ID: " + id); // Generic error
        }
        questionRepo.deleteById(id);
    }

    public ResponseEntity<List<Integer>> generateQuestionByType(String questionType) {
        List<Question> questions = questionRepo.findByType(questionType);

        if (questions.isEmpty()) {
            throw new RuntimeException("No questions found for type: " + questionType);
        }
        List<Integer> questionIds = new ArrayList<>();
        for (Question question : questions) {
            questionIds.add(Math.toIntExact(question.getId()));
        }
        return ResponseEntity.ok(questionIds);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(List<Integer> questionIds) {

        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Integer questionId : questionIds) {
            Question question = questionRepo.getQuestionById(Long.valueOf(questionId));
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(question.getId());
            questionWrapper.setQuestion_title(question.getQuestion_title());
            questionWrapper.setQuestion_type(question.getQuestion_type());
            questionWrapper.setFirst_option(question.getFirst_option());
            questionWrapper.setSecond_option(question.getSecond_option());
            questionWrapper.setThird_option(question.getThird_option());
            questionWrapper.setFourth_option(question.getFourth_option());
            questionWrappers.add(questionWrapper);
        }
        return ResponseEntity.ok(questionWrappers);
    }

    public Integer calculate(List<Answer> answer) {
        int result = 0;
        for (int i = 0; i < answer.size(); i++) {
            Question question = questionRepo.getQuestionById(answer.get(i).getId());
            if (question.getRight_answer().equals(answer.get(i).getAnswer())){
                result++;
            }
        }
        return result;
    }
}

package com.quiz.quiz_service.feign;

import com.quiz.quiz_service.model.Answer;
import com.quiz.quiz_service.model.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate/{questionType}")
    public ResponseEntity<List<Integer>> generateQuestion(@PathVariable String questionType);

    // get the list of the questions based on the question ids
    @PostMapping("question/getQuestionsByIds")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(@RequestBody List<Integer> questionIds);
    // calculate the score based on the answer

    @PostMapping("question/submit")
    public Integer calculate(@RequestBody List<Answer> answer);
}

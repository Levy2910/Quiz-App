package com.quiz.quiz_service.controller;
import com.quiz.quiz_service.model.Answer;
import com.quiz.quiz_service.model.QuestionWrapper;
import com.quiz.quiz_service.model.Quizz;
import com.quiz.quiz_service.service.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quizz")
public class QuizzController {
    @Autowired
    QuizzService quizzService;

    @PostMapping("addQuizzByType")
    public ResponseEntity<Quizz> createQuizzByType(@RequestParam("quizzName") String quizzName,
                                                   @RequestParam("type") String type) {
        try {
            Quizz createdQuizz = quizzService.createQuizzByType(quizzName, type);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdQuizz); // Return 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Return 500 Internal Server Error
        }
    }

    @GetMapping("get/{id}")
    public List<QuestionWrapper> getQuizzById(@PathVariable("id") Long id) {
        return quizzService.getQuizzById(id);
    }

    @PostMapping("submit")
    public int submit(@RequestBody List<Answer> answer) {
        return quizzService.calculateScore( answer);
    }
}

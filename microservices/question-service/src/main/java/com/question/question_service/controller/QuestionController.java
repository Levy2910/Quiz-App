package com.question.question_service.controller;

import com.question.question_service.model.Answer;
import com.question.question_service.model.Question;
import com.question.question_service.model.QuestionWrapper;
import com.question.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    //get all questions
    @GetMapping("questions")
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionService.getQuestions();
        if (questions.isEmpty()) {
            return ResponseEntity.noContent().build(); // Returns 204 No Content if no questions found
        }
        return ResponseEntity.ok(questions); // Returns 200 OK with the list of questions
    }

    //get 1 question
    @GetMapping("question/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        try {
            Question question = questionService.getQuestionById(id);
            return ResponseEntity.ok(question); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }

    //add 1 question
    @PostMapping("question")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        try {
            Question createdQuestion = questionService.addQuestion(question);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion); // Return 201 Created with the question
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Return 500 Internal Server Error
        }
    }

    // add many questions
    @PostMapping("manyquestions")
    public ResponseEntity<List<Question>> addManyQuestions(@RequestBody List<Question> questions) {
        try{
            List<Question> createdQuestions = questionService.addQuestions(questions);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestions);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // delete 1 question
    @DeleteMapping("question/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        try {
            questionService.deleteQuestionById(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 Internal Server Error
        }
    }

    //generate list of questions
    @GetMapping("generate/{questionType}")
    public ResponseEntity<List<Integer>> generateQuestion(@PathVariable String questionType) {
        return questionService.generateQuestionByType(questionType);
    }


    // get the list of the questions based on the question ids
    @PostMapping("getQuestionsByIds")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestionsByIds(questionIds);
    }
    // calculate the score based on the answer

    @PostMapping("submit")
    public Integer calculate(@RequestBody List<Answer> answer) {
        return questionService.calculate(answer);
    }
    //data to test: [
    //    {
    //        "id": 1,
    //        "answer": "33"
    //    },
    //    {
    //        "id": 12,
    //        "answer": "false"
    //    },
    //    {
    //        "id": 17,
    //        "answer": "32 bits"
    //    }
    //]

}

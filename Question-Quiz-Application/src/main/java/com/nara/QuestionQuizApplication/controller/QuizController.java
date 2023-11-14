package com.nara.QuestionQuizApplication.controller;

import com.nara.QuestionQuizApplication.models.QuestionWrapper;
import com.nara.QuestionQuizApplication.models.Quiz;
import com.nara.QuestionQuizApplication.models.Response;
import com.nara.QuestionQuizApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int noOfQuestions, @RequestParam String title){
        return quizService.createQuiz(category, noOfQuestions, title);
        //return new ResponseEntity<>("I am here", HttpStatus.OK);
    }

    @GetMapping("get/{quiz_id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("quiz_id") Integer id){
        return quizService.getAllQuestionsById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> quizResultCalculation(@PathVariable("id") Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

}

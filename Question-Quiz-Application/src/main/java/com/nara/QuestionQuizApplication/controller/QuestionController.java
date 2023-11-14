package com.nara.QuestionQuizApplication.controller;

import com.nara.QuestionQuizApplication.models.Question;
import com.nara.QuestionQuizApplication.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@ResponseStatus
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @PostMapping("addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PostMapping("addAllQuestions")
    public ResponseEntity<List<Question>> addMultipleQuestions(@RequestBody List<Question> questions){
        return questionService.addAllQuestions(questions);
    }

    @GetMapping("{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable("category") String category){
        return questionService.addAllQuestionsByCategory(category);
    }
}

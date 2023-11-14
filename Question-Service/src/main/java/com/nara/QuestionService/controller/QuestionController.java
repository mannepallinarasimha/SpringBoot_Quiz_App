package com.nara.QuestionService.controller;


import com.nara.QuestionService.models.Question;
import com.nara.QuestionService.models.QuestionWrapper;
import com.nara.QuestionService.models.Response;
import com.nara.QuestionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    //generate Quiz
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestions(@RequestParam String category, @RequestParam Integer noOfQuestions){
        return questionService.getQuestions(category, noOfQuestions);
    }
    //get questionsByQuestionId(questionId)
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsListById(@RequestBody List<Integer> ids){
        return questionService.getQuestionsByIds(ids);
    }
    //CalculateScore

    @PostMapping("getScore")
    public ResponseEntity<Integer> getCalculateScore(@RequestBody List<Response> responses){
        return questionService.calculateScoreBasedOnResponse(responses);
    }
}

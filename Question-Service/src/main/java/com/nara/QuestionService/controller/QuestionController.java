package com.nara.QuestionService.controller;


import com.nara.QuestionService.models.Question;
import com.nara.QuestionService.models.QuestionWrapper;
import com.nara.QuestionService.models.Response;
import com.nara.QuestionService.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@ResponseStatus
@Slf4j
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @Autowired
    private Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        System.out.println("Load on port right now is:: "+ environment.getProperty("local.server.port"));

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

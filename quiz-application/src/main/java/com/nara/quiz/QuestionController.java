package com.nara.quiz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="question")
public class QuestionController {

    @GetMapping(path="allQuestions")
    public String getAllQuestions(){
        return "Hi These are the Questions...";
    }
}

package com.nara.QuizService.feign;

import com.nara.QuizService.models.QuestionWrapper;
import com.nara.QuizService.models.Response;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface FeignQuizInterface {

    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestions(@RequestParam String category, @RequestParam Integer noOfQuestions);
    //get questionsByQuestionId(questionId)
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsListById(@RequestBody List<Integer> ids);
    //CalculateScore

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getCalculateScore(@RequestBody List<Response> responses);
}

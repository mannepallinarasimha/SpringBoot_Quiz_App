package com.nara.QuizService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nara.QuizService.feign.FeignQuizInterface;
import com.nara.QuizService.models.*;
import com.nara.QuizService.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    ObjectMapper mapper;
//    @Autowired
//    private QuestionRepository questionRepository;
    @Autowired
    FeignQuizInterface feignQuizInterface;
    public ResponseEntity<String> createQuiz( QuizDto quizDto) {
        try{
           // List<Question> questions = questionRepository.findByRandomQuesionsCategory(category, noOfQuestions);
            //List<Question> questions = questionRepository.findByCategory(category).stream().limit(noOfQuestions).toList();
          //  Quiz quiz = new Quiz();
           // quiz.setTitle(title);
            //quiz.setQuestions(questions);
            //quizRepository.save(quiz);
            List<Integer> questionsListIds = feignQuizInterface.getQuestions(quizDto.getCategory(), quizDto.getNoOfQuestions()).getBody();
            //List<QuestionWrapper> questionWrapperList = feignQuizInterface.getQuestionsListById(questionsListIds).getBody();
              Quiz quiz = new Quiz();
             quiz.setTitle(quizDto.getTitle());
            quiz.setQuestionIds(questionsListIds);
            quizRepository.save(quiz);
            return new ResponseEntity<>("Created Questions", HttpStatus.CREATED);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<List<QuestionWrapper>> getAllQuestionsById(Integer id) {
        try{
            List<Integer> questionIds = quizRepository.findById(id).get().getQuestionIds();
            List<QuestionWrapper> questionWrapperList = feignQuizInterface.getQuestionsListById(questionIds).getBody();
//            List<QuestionWrapper> questionWrapperList = new ArrayList<>();
//            for (Question question : questions) {
//                QuestionWrapper questionWrapper = mapper.convertValue(question, QuestionWrapper.class);
//                questionWrapperList.add(questionWrapper);
//            }

            return new ResponseEntity<>(questionWrapperList, HttpStatus.OK);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        int result =0;
        ResponseEntity<Integer> calculateScore = feignQuizInterface.getCalculateScore(responses);
        //Integer body = ;
//        int i=0;
//        Quiz quiz = quizRepository.findById(id).get();
//        List<Question> questions = quiz.getQuestions();
//        for (Response response: responses) {
//            if(response.getAnsFromCandidate().equals(questions.get(i).getRightAnswer())){
//                result++;
//            }
//            i++;
//        }
        return new ResponseEntity<Integer>(calculateScore.getBody(), HttpStatus.OK);
    }
}

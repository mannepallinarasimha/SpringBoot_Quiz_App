package com.nara.QuestionService.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nara.QuestionService.models.Question;
import com.nara.QuestionService.models.QuestionWrapper;
import com.nara.QuestionService.models.Response;
import com.nara.QuestionService.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    ObjectMapper mapper;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<List<Question>>( questionRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<List<Question>>( new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        try {
            return new ResponseEntity<Question>( questionRepository.save(question), HttpStatus.CREATED);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return new ResponseEntity<Question>(new Question(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> addAllQuestions(List<Question> questions) {
        try {
            return new ResponseEntity<List<Question>>( questionRepository.saveAll(questions), HttpStatus.CREATED);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> addAllQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<List<Question>>( questionRepository.findByCategory(category), HttpStatus.CREATED);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestions(String category, Integer noOfQuestions) {
        List<Integer> questions = questionRepository.findByRandomQuesionsCategory(category, noOfQuestions);
        return new ResponseEntity<List<Integer>>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(List<Integer> ids) {
        List<Question> questionsByIds = questionRepository.findAllById(ids);
        List<QuestionWrapper> questionWrapperList = new ArrayList<>();
        for (Question question: questionsByIds) {
            QuestionWrapper questionWrapper = mapper.convertValue(question, QuestionWrapper.class);
            questionWrapperList.add(questionWrapper);
        }
        return new ResponseEntity<List<QuestionWrapper>>(questionWrapperList, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScoreBasedOnResponse(List<Response> responses) {
        int right =0;
        for (Response respons : responses) {
           Question question = questionRepository.findById(respons.getId()).get();
           if(question.getRightAnswer().equals(respons.getAnswerFromCandidate())){
               right ++;
           }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}

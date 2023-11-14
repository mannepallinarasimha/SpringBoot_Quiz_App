package com.nara.QuestionQuizApplication.service;

import com.nara.QuestionQuizApplication.models.Question;
import com.nara.QuestionQuizApplication.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

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
}

package com.nara.QuestionQuizApplication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nara.QuestionQuizApplication.models.Question;
import com.nara.QuestionQuizApplication.models.QuestionWrapper;
import com.nara.QuestionQuizApplication.models.Quiz;
import com.nara.QuestionQuizApplication.models.Response;
import com.nara.QuestionQuizApplication.repository.QuestionRepository;
import com.nara.QuestionQuizApplication.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuizService {

    @Autowired
    private QuizRepository  quizRepository;

    @Autowired
    ObjectMapper mapper;
    @Autowired
    private QuestionRepository questionRepository;
    public ResponseEntity<String> createQuiz(String category, int noOfQuestions, String title) {
        try{
            List<Question> questions = questionRepository.findByRandomQuesionsCategory(category, noOfQuestions);
            //List<Question> questions = questionRepository.findByCategory(category).stream().limit(noOfQuestions).toList();
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizRepository.save(quiz);
            return new ResponseEntity<>("Created Questions", HttpStatus.CREATED);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<List<QuestionWrapper>> getAllQuestionsById(Integer id) {
        try{
            List<Question> questions = quizRepository.findById(id).get().getQuestions();
            List<QuestionWrapper> questionWrapperList = new ArrayList<>();
            for (Question question : questions) {
                QuestionWrapper questionWrapper = mapper.convertValue(question, QuestionWrapper.class);
                questionWrapperList.add(questionWrapper);
            }

            return new ResponseEntity<>(questionWrapperList, HttpStatus.OK);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        int result =0;
        int i=0;
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        for (Response response: responses) {
            if(response.getAnsFromCandidate().equals(questions.get(i).getRightAnswer())){
                result++;
            }
            i++;
        }
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
}

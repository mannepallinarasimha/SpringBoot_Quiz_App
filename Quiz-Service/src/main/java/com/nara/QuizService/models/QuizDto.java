package com.nara.QuizService.models;

import lombok.Data;

@Data
public class QuizDto {

    private String category;
    private  int noOfQuestions;
    private  String title;
}

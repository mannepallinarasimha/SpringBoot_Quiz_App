package com.nara.QuestionQuizApplication.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {

    private Integer id;
    private String ansFromCandidate;
}

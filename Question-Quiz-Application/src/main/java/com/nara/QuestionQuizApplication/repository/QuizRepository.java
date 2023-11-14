package com.nara.QuestionQuizApplication.repository;

import com.nara.QuestionQuizApplication.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}

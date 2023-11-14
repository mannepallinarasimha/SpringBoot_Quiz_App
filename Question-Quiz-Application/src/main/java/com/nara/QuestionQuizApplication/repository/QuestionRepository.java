package com.nara.QuestionQuizApplication.repository;

import com.nara.QuestionQuizApplication.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    public List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE category=:category ORDER BY Random() LIMIT :noOfQuestions ", nativeQuery = true)
    List<Question> findByRandomQuesionsCategory(String category, int noOfQuestions);
}

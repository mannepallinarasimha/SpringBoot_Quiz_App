package com.nara.QuestionService.repository;


import com.nara.QuestionService.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    public List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q WHERE category=:category ORDER BY Random() LIMIT :noOfQuestions ", nativeQuery = true)
    List<Integer> findByRandomQuesionsCategory(String category, int noOfQuestions);
}

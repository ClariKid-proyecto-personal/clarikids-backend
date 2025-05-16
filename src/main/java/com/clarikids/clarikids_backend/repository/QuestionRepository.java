package com.clarikids.clarikids_backend.repository;
import com.clarikids.clarikids_backend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}

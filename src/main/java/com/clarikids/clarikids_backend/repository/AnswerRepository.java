package com.clarikids.clarikids_backend.repository;

import com.clarikids.clarikids_backend.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}

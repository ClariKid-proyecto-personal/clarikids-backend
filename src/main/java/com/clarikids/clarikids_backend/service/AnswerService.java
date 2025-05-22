package com.clarikids.clarikids_backend.service;

import com.clarikids.clarikids_backend.model.Answer;
import com.clarikids.clarikids_backend.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> findByQuestionId(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }
}
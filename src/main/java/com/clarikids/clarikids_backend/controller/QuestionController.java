package com.clarikids.clarikids_backend.controller;

import com.clarikids.clarikids_backend.model.Question;
import com.clarikids.clarikids_backend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        question.setCreatedAt(LocalDateTime.now());
        question.setAnswered(false); // aún no hay respuestas
        return questionRepository.save(question);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionRepository.deleteById(id);
    }

}

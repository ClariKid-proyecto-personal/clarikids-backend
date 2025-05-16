package com.clarikids.clarikids_backend.controller;

import com.clarikids.clarikids_backend.model.Answer;
import com.clarikids.clarikids_backend.model.Question;
import com.clarikids.clarikids_backend.repository.AnswerRepository;
import com.clarikids.clarikids_backend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping
    public Answer createAnswer(@RequestBody Answer answer) {
        // Buscar la pregunta asociada
        Optional<Question> questionOptional = questionRepository.findById(answer.getQuestion().getId());

        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            question.setAnswered(true); // Marcamos como respondida
            answer.setQuestion(question);
            answer.setCreatedAt(LocalDateTime.now());
            return answerRepository.save(answer);
        } else {
            throw new RuntimeException("Pregunta no encontrada");
        }
    }

    @GetMapping
        public List<Answer> getAllAnswers() {
            return answerRepository.findAll();
    }

}

package com.clarikids.clarikids_backend.controller;

import com.clarikids.clarikids_backend.dto.QuestionWithAnswerDTO;
import com.clarikids.clarikids_backend.model.Question;
import com.clarikids.clarikids_backend.repository.QuestionRepository;
import com.clarikids.clarikids_backend.utils.TextUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

 
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

   @GetMapping("/search")
    public List<QuestionWithAnswerDTO> searchQuestions(
    @RequestParam String text,
        @RequestParam String subject) {

            String normalizedInput = TextUtils.normalize(text);

            return questionRepository.findAll().stream()
                .filter(q -> TextUtils.normalize(q.getSubject()).equalsIgnoreCase(TextUtils.normalize(subject)))
                .filter(Question::isAnswered)
                .filter(q -> TextUtils.isFuzzyMatch(q.getQuestionText(), normalizedInput))
                .map(q -> new QuestionWithAnswerDTO(
                    q.getQuestionText(),
                    q.getSubject(),
                    q.getAnswers().isEmpty() ? null : q.getAnswers().get(0).getAnswerText()))
                .collect(Collectors.toList());
        }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionRepository.deleteById(id);
    }

}

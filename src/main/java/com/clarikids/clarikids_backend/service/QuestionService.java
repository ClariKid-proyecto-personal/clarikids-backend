package com.clarikids.clarikids_backend.service;

import com.clarikids.clarikids_backend.model.Question;
import com.clarikids.clarikids_backend.model.Answer;
import com.clarikids.clarikids_backend.repository.QuestionRepository;
import com.clarikids.clarikids_backend.repository.AnswerRepository;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    private static final int SIMILARITY_THRESHOLD = 5; 

    public String askQuestion(String questionText, String subject) {
        String normalizedInput = normalize(questionText);
        LevenshteinDistance ld = new LevenshteinDistance();

        List<Question> allQuestions = questionRepository.findAll();

        for (Question q : allQuestions) {
            String normalizedDB = normalize(q.getQuestionText());
            int distance = ld.apply(normalizedInput, normalizedDB);

            if (distance <= SIMILARITY_THRESHOLD) {
                q.setTimesAsked(q.getTimesAsked() + 1);
                questionRepository.save(q);

                List<Answer> answers = answerRepository.findByQuestionId(q.getId());
                if (!answers.isEmpty()) {
                    return answers.get(0).getAnswerText();
                } else {
                    return "¡Buena pregunta! Todavía no la hemos respondido, pero el profe lo verá pronto.";
                }
            }
        }

        
        Question newQ = new Question();
        newQ.setQuestionText(questionText);
        newQ.setSubject(subject);
        newQ.setCreatedAt(LocalDateTime.now());
        newQ.setAnswered(false);
        newQ.setTimesAsked(1);
        questionRepository.save(newQ);

        return "¡Gracias por tu pregunta! Aún no tengo respuesta, pero la he guardado para el profe.";
    }

    private String normalize(String input) {
        if (input == null) return "";
        return Normalizer.normalize(input.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "") // quita tildes
                .replaceAll("[^a-z0-9 ]", "") // quita signos
                .trim();
    }
}
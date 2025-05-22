// Controlador de preguntas funcional sin login ni tokens
package com.clarikids.clarikids_backend.controller;

import com.clarikids.clarikids_backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/ask")
    public Map<String, String> ask(@RequestBody Map<String, String> body) {
        String question = body.get("text");
        String subject = body.get("subject");

        String response = questionService.askQuestion(question, subject);

        return Map.of("answer", response);
    }
    
}
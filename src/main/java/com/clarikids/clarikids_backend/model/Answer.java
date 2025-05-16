package com.clarikids.clarikids_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;



@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answerText;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAnswerText() { return answerText; }
    public void setAnswerText(String answerText) { this.answerText = answerText; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
}

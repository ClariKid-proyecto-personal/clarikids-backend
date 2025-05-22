package com.clarikids.clarikids_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    private LocalDateTime createdAt;

    private boolean isAnswered;

    @Column(nullable = false)
    private String subject;

    private int timesAsked = 1; // se inicializa en 1 al crear la pregunta

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Answer> answers;

    // Constructores
    public Question() {}

    public Question(String questionText, String subject) {
        this.questionText = questionText;
        this.subject = subject;
        this.createdAt = LocalDateTime.now();
        this.isAnswered = false;
        this.timesAsked = 1;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public boolean isAnswered() { return isAnswered; }
    public void setAnswered(boolean answered) { isAnswered = answered; }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public int getTimesAsked() { return timesAsked; }
    public void setTimesAsked(int timesAsked) { this.timesAsked = timesAsked; }

    public void incrementTimesAsked() {
        this.timesAsked++;
    };

};
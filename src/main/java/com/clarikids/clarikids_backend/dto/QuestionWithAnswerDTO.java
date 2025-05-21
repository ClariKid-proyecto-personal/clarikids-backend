package com.clarikids.clarikids_backend.dto;

public class QuestionWithAnswerDTO {
    private String questionText;
    private String subject;
    private String answerText;

    public QuestionWithAnswerDTO(String questionText, String subject, String answerText) {
        this.questionText = questionText;
        this.subject = subject;
        this.answerText = answerText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getSubject() {
        return subject;
    }

    public String getAnswerText() {
        return answerText;
    }
}
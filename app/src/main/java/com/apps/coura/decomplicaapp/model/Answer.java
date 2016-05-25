package com.apps.coura.decomplicaapp.model;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class Answer {

    private String answerText;
    private boolean isCorrectAnswer;

    public Answer(String answerText, boolean isCorrectAnswer) {
        this.isCorrectAnswer = isCorrectAnswer;
        this.answerText = answerText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }
}

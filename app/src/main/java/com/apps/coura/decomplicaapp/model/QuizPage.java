package com.apps.coura.decomplicaapp.model;

import java.util.List;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class QuizPage {
    private String question;
    private List<Answer> answers;
    private int maxPoints;
    private int goldCoins;

    public QuizPage(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public void setGoldCoins(int goldCoins) {
        this.goldCoins = goldCoins;
    }
}

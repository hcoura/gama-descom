package com.apps.coura.decomplicaapp.model;

/**
 * Created by Henrique Coura on 29/05/2016.
 */
public class Score {

    private int module;
    private int question_index;
    private int score;

    public Score(int module, int question_index, int score) {
        this.score = score;
        this.question_index = question_index;
        this.module = module;
    }

    public int getScore() {
        return score;
    }

    public int getQuestionIndex() {
        return question_index;
    }

    public int getModule() {
        return module;
    }
}

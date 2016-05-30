package com.apps.coura.decomplicaapp.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henrique Coura on 29/05/2016.
 */
public class User {

    private static final String PREFS_NAME = "user_prefs";
    private static final String SCORE_KEY = "scores";

    public static void setScore(Context context, int module, int question_index, int points) {
        List<Score> scores = getScores(context);

        ArrayList<Score> newScores = new ArrayList<>();
        boolean hasAddedScore = false;
        Score score = new Score(module, question_index, points);

        if (scores != null) {
            for (Score s: scores) {
                if (s.getModule() == module && s.getQuestionIndex() == question_index) {
                    newScores.add(score);
                    hasAddedScore = true;
                } else {
                    newScores.add(s);
                }
            }
        }

        if (!hasAddedScore) newScores.add(score);

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonScores = gson.toJson(newScores);
        prefs.edit().putString(SCORE_KEY, jsonScores).apply();

    }

    private static List<Score> getScores(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Score>>() {}.getType();
        ArrayList<Score> scores;

        String jsonScores = prefs.getString(SCORE_KEY, "");

        scores = gson.fromJson(jsonScores, type);
        return scores;
    }

    public static int getScore(Context context) {
        int totalScore = 0;
        List<Score> scores = getScores(context);
        if (scores == null) return totalScore;

        for (Score s :
                scores) {
            totalScore += s.getScore();
        }

        return totalScore;
    }
}

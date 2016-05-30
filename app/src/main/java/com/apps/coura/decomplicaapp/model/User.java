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
    private static final String COINS_KEY = "coins";
    private static final String COMPLETED_QUIZ_KEY = "completed_quizzes";
    private static final String EXPERIENCE_KEY = "experience";
    private static final String LEVEL_KEY = "level";

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

    public static void addGoldCoins(Context context, int coins) {
        int currentCoins = getGoldCoins(context);

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(COINS_KEY, currentCoins + coins).apply();
    }

    public static int getGoldCoins(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(COINS_KEY, 0);
    }

    public static boolean hasCompletedQuiz(Context context, int module, int question_index) {
        List<String> completedQuizzes = getCompletedQuizzes(context);
        if (completedQuizzes == null) return false;

        String quiz = ""+ module + question_index;

        for (String q :
                completedQuizzes) {
            if (q.equals(quiz)) return true;
        }
        return false;
    }

    private static List<String> getCompletedQuizzes(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        ArrayList<String> completedQuizzes;

        String jsonCompleted = prefs.getString(COMPLETED_QUIZ_KEY, "");

        completedQuizzes = gson.fromJson(jsonCompleted, type);
        return completedQuizzes;
    }

    public static void completedQuiz(Context context, int module, int question_index) {
        if (hasCompletedQuiz(context, module, question_index)) return;
        List<String> oldCompletedQuiz = getCompletedQuizzes(context);
        ArrayList<String> completedQuizzes = new ArrayList<>();

        if (!completedQuizzes.isEmpty()) {
            for (String q :
                    oldCompletedQuiz) {
                completedQuizzes.add(q);
            }
        }


        String quiz = ""+ module + question_index;
        completedQuizzes.add(quiz);

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonCompletedQuiz = gson.toJson(completedQuizzes);
        prefs.edit().putString(COMPLETED_QUIZ_KEY, jsonCompletedQuiz).apply();
    }

    public static void setExperience(Context context, int exp) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(EXPERIENCE_KEY, exp).apply();
    }

    public static int getExperience(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(EXPERIENCE_KEY, 0);
    }

    public static void setUserLevel(Context context, int lvl) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(LEVEL_KEY, lvl).apply();
    }

    public static int getUserLevel(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(LEVEL_KEY, 0);
    }
}

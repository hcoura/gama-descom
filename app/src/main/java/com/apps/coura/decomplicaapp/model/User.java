package com.apps.coura.decomplicaapp.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.apps.coura.decomplicaapp.R;
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
    private static final String MODULE_LAST_POS_KEY = "module_last_pos";
    private static final String UNLOCKED_MODULE_KEY = "unlocked_module";
    private static final String USERNAME_KEY = "user_name";
    private static final String AVATAR_ID_KEY = "avatar_id";
    private static final String IS_LOGGED_KEY = "isLogged";
    private static final String SPENT_COINS_KEY = "spent_coins";

    private static final String[] mTitles = {"Juninho em História", "Nerd em História", "Universitário em História",
            "Mestre em História", "Ninja em História"};

    public static final int[] mAvatarIds = {
            R.drawable.hulk,
            R.drawable.ninja,
            R.drawable.batman,
            R.drawable.storm
    };

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

    public static int getModuleScore(Context context, int module) {
        int totalScore = 0;
        List<Score> scores = getScores(context);
        if (scores == null) return totalScore;

        for (Score s :
                scores) {
            if (s.getModule() == module && s.getQuestionIndex() != -1) {
                totalScore += s.getScore();
            }
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

        if (oldCompletedQuiz != null && !oldCompletedQuiz.isEmpty()) {
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

    public static void setModuleLastPosition(Context context, int module, int pos) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(MODULE_LAST_POS_KEY + module, pos).apply();
    }

    public static int getModuleLastPosition(Context context, int module) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(MODULE_LAST_POS_KEY + module, 0);
    }

    public static void setUnlockedModule(Context context, int module) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(UNLOCKED_MODULE_KEY, module).apply();
    }

    public static int getUnlockedModule(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(UNLOCKED_MODULE_KEY, 0);
    }

    public static String getUserTitle(Context context) {
        return mTitles[getUserLevel(context)];
    }

    public static void setUserName(Context context, String username) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(USERNAME_KEY, username).apply();
    }

    public static String getUserName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USERNAME_KEY, "");
    }

    public static void setAvatarId(Context context, int avatar_id) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(AVATAR_ID_KEY, avatar_id).apply();
    }

    public static int getAvatarId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(AVATAR_ID_KEY, 0);
    }

    public static void setIsLogged(Context context, boolean logged) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(IS_LOGGED_KEY, logged).apply();
    }

    public static boolean getIsLogged(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(IS_LOGGED_KEY, false);
    }

    public static void setSpentCoins(Context context, int coins) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(SPENT_COINS_KEY, coins).apply();
    }

    public static int getSpentCoins(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(SPENT_COINS_KEY, 0);
    }
}

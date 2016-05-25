package com.apps.coura.decomplicaapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class ModuleFactory {

    public static ArrayList<Module> getListOfModules() {

        ArrayList<Module> modules = new ArrayList<>();

        // Module 1
        Module module1 = new Module("Módulo 1");
        VideoPage videoPage1 = new VideoPage("sDC3OYLQCyo");
        VideoPage videoPage2 = new VideoPage("sDC3OYLQCyo");
        List<VideoPage> videoPages1 = Arrays.asList(videoPage1, videoPage2);
        module1.setVideoPages(videoPages1);
        QuizPage quizPage1 = new QuizPage("Quiz Page 1");

        List<Answer> answers = Arrays.asList(
                new Answer("Resposta 1 - A Correta", true),
                new Answer("Resposta 2 - Errada", false),
                new Answer("Resposta 3 - Errada", false),
                new Answer("Resposta 4 - Errada", false),
                new Answer("Resposta 5 - Errada", false)
                );

        quizPage1.setAnswers(answers);

        QuizPage quizPage2 = new QuizPage("Quiz Page 2");
        quizPage2.setAnswers(answers);
        List<QuizPage> quizPages1 = Arrays.asList(quizPage1, quizPage2);
        module1.setQuizPages(quizPages1);

        // Module 2
        Module module2 = new Module("Módulo 2");
        VideoPage videoPage3 = new VideoPage("sDC3OYLQCyo");
        VideoPage videoPage4 = new VideoPage("sDC3OYLQCyo");
        List<VideoPage> videoPages2 = Arrays.asList(videoPage3, videoPage4);
        module2.setVideoPages(videoPages2);
        QuizPage quizPage3 = new QuizPage("Quiz Page 3");
        quizPage3.setAnswers(answers);
        QuizPage quizPage4 = new QuizPage("Quiz Page 4");
        quizPage4.setAnswers(answers);
        List<QuizPage> quizPages2 = Arrays.asList(quizPage3, quizPage4);
        module2.setQuizPages(quizPages2);

        modules.add(module1);
        modules.add(module2);

        return modules;

    }

}

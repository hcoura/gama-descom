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
        VideoPage videoPage1 = new VideoPage("Video Page 1");
        VideoPage videoPage2 = new VideoPage("Video Page 2");
        List<VideoPage> videoPages1 = Arrays.asList(videoPage1, videoPage2);
        module1.setVideoPages(videoPages1);
        QuizPage quizPage1 = new QuizPage("Quiz Page 1");
        QuizPage quizPage2 = new QuizPage("Quiz Page 2");
        List<QuizPage> quizPages1 = Arrays.asList(quizPage1, quizPage2);
        module1.setQuizPages(quizPages1);

        // Module 2
        Module module2 = new Module("Módulo 2");
        VideoPage videoPage3 = new VideoPage("Video Page 3");
        VideoPage videoPage4 = new VideoPage("Video Page 4");
        List<VideoPage> videoPages2 = Arrays.asList(videoPage3, videoPage4);
        module2.setVideoPages(videoPages2);
        QuizPage quizPage3 = new QuizPage("Quiz Page 3");
        QuizPage quizPage4 = new QuizPage("Quiz Page 4");
        List<QuizPage> quizPages2 = Arrays.asList(quizPage3, quizPage4);
        module2.setQuizPages(quizPages2);

        modules.add(module1);
        modules.add(module2);

        return modules;

    }

}

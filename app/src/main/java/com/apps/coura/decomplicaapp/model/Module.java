package com.apps.coura.decomplicaapp.model;

import java.util.List;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class Module {

    private String title;
    private List<VideoPage> videoPages;
    private List<QuizPage> quizPages;

    public Module(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<VideoPage> getVideoPages() {
        return videoPages;
    }

    public void setVideoPages(List<VideoPage> videoPages) {
        this.videoPages = videoPages;
    }

    public List<QuizPage> getQuizPages() {
        return quizPages;
    }

    public void setQuizPages(List<QuizPage> quizPages) {
        this.quizPages = quizPages;
    }

}

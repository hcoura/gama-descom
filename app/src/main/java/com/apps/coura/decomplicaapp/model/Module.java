package com.apps.coura.decomplicaapp.model;

import java.util.List;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class Module {

    private String title;
    private List<VideoPage> videoPages;
    private List<QuizPage> quizPages;
    private int icon;
    private int lockedIcon;

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

    public int getLockedIcon() {
        return lockedIcon;
    }

    public void setLockedIcon(int lockedIcon) {
        this.lockedIcon = lockedIcon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}

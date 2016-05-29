package com.apps.coura.decomplicaapp.model;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class VideoPage {
    private String videoUrl;
    private String videoTitle;

    public VideoPage(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}

package com.apps.coura.decomplicaapp.model;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class VideoPage {
    private String videoUrl;

    public VideoPage(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}

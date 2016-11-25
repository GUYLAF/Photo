package com.guylaf.photo.photos;

/**
 * Created by guyla on 24/11/2016.
 */

public class Photo {
    String title;
    String url;

    public Photo(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
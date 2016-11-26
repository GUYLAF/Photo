package com.guylaf.photo;

import com.guylaf.photo.FlickrPhotosDto;

/**
 * Created by guyla on 25/11/2016.
 */

public class FlickrResponseDto {
    private FlickrPhotosDto photos;
    private String stat;

    public FlickrResponseDto(FlickrPhotosDto photos, String stat) {
        this.photos = photos;
        this.stat = stat;
    }

    public FlickrPhotosDto getPhotos() {
        return photos;
    }

    public void setPhotos(FlickrPhotosDto photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}



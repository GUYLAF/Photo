package com.guylaf.photo.flickr;

import com.guylaf.photo.photos.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guyla on 25/11/2016.
 */

public class Converter {

        public List<Photo> convert(FlickrResponseDto response){
            List<Photo> convertedPhotos = new ArrayList<>();
            List<PhotoDto> photoDtos = response.getPhotos().getPhoto();

            for (PhotoDto photoDto : photoDtos){
                String title = photoDto.getTitle();
                long  farm = photoDto.getFarm();
                String server = photoDto.getServer();
                String id = photoDto.getId();
                String secret = photoDto.getSecret();
                Photo photo = new Photo(title, "https://farm"+farm+".static.flickr.com/"+server+"/"+id+"_"+secret+".jpg");
                convertedPhotos.add(photo);
            }
            return convertedPhotos;
        }

}

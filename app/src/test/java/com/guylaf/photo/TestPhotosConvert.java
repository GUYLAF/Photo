package com.guylaf.photo;

import com.guylaf.photo.flickr.Converter;
import com.guylaf.photo.flickr.FlickrPhotosDto;
import com.guylaf.photo.flickr.FlickrResponseDto;
import com.guylaf.photo.flickr.PhotoDto;
import com.guylaf.photo.photos.Photo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by guyla on 25/11/2016.
 */

public class TestPhotosConvert {

    @Test
    public void test() {

        List<PhotoDto> listphotos = new ArrayList<>();
        listphotos.add(new PhotoDto("31192354826", "59807308@N08", "93b1b66781", "5456", 6, "Mastik", 1, 0, 0));
        listphotos.add(new PhotoDto("30390851924", "58572369@N08", "fd30a137c7", "5472", 6, "PHOTOGRAPHIE DU PETIT CHIEN - 2016", 1, 0, 0));

        FlickrPhotosDto photos = new FlickrPhotosDto(1, 6633, 5, "33162", listphotos);

        FlickrResponseDto response = new FlickrResponseDto(photos,"OK");

        List<Photo> expectedList = new ArrayList<>();
        expectedList.add(new Photo("Mastik", "https://farm6.static.flickr.com/5456/31192354826_93b1b66781.jpg"));
        expectedList.add(new Photo("PHOTOGRAPHIE DU PETIT CHIEN - 2016", "https://farm6.static.flickr.com/5472/30390851924_fd30a137c7.jpg"));
        Converter convert = new Converter();
        List<Photo> convertList = convert.convert(response);

        assertEquals(expectedList.toString(),convertList.toString());
    }

}

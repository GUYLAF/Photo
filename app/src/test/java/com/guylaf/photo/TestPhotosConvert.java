package com.guylaf.photo;

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
        listphotos.add(new PhotoDto("28087377815", "67221971@N06", "80c8f2e753", "7308", 8, "Monde Gris", 1, 0, 0));
        listphotos.add(new PhotoDto("27425564203", "67221971@N06", "8204d2863d", "7303", 8, "L'Arbre Blanc", 1, 0, 0));
        listphotos.add(new PhotoDto("24077385539", "67221971@N06", "7b04e4d47a", "1444", 2, "Les couleurs du vent", 1, 0, 0));


        FlickrPhotosDto photos = new FlickrPhotosDto(1, 30, 5, "147", listphotos);

        FlickrResponseDto response = new FlickrResponseDto(photos,"OK");

        List<Photo> expectedList = new ArrayList<>();
        expectedList.add(new Photo("Monde Gris\"", "https://farm8.static.flickr.com/7308/28087377815_80c8f2e753.jpg"));
        expectedList.add(new Photo("L'Arbre Blanc\"", "https://farm8.static.flickr.com/7303/27425564203_8204d2863d.jpg"));
        expectedList.add(new Photo("Les couleurs du vent\"", "https://farm2.static.flickr.com/1444/24077385539_7b04e4d47a.jpg"));

        Converter convert = new Converter();
        List<Photo> convertList = convert.convert(response);

        assertEquals(expectedList.toString(),convertList.toString());
    }

}

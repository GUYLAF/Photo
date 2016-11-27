package com.guylaf.photo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guyla on 25/11/2016.
 */

public class BoundService extends Service {

    private final IBinder binder = new ServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class ServiceBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }

    public List<Photo> getPhoto () {
        List<PhotoDto> listphotos = new ArrayList<>();
        listphotos.add(new PhotoDto("28087377815", "67221971@N06", "80c8f2e753", "7308", 8, "t1", 1, 0, 0));//Monde Gris
        listphotos.add(new PhotoDto("27425564203", "67221971@N06", "8204d2863d", "7303", 8, "t2", 1, 0, 0));//L'Arbre Blanc
        listphotos.add(new PhotoDto("24077385539", "67221971@N06", "7b04e4d47a", "1444", 2, "t3", 1, 0, 0));//Les couleurs du vent

        FlickrPhotosDto photos = new FlickrPhotosDto(1, 30, 5, "147", listphotos);

        FlickrResponseDto response = new FlickrResponseDto(photos,"OK");

        Converter convert = new Converter();
        List<Photo> convertList = convert.convert(response);

        return convertList;
    }
}

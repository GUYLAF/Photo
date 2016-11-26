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
        List<Photo> list = new ArrayList<>();
        list.add(new Photo("Title1", "http://www.ecobase21.net/Paysage/Liens/Paysagesnaturels5.jpg"));
        list.add(new Photo("Title2", "http://www.unesourisetmoi.info/wallpaper_24/images/paysages-de-reve-06.jpg"));
        list.add(new Photo("Title3", "http://testeurs.callandgo.fr/wp-content/uploads/Paysage-de-neige-13.jpg"));
        return list;
    }
}

package com.guylaf.photo;

import android.content.Context;
import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.net.URL;
import java.util.List;

/**
 * Created by guyla on 30/11/2016.
 */

public class PhotoPersistenceManager {

    public PhotoPersistenceManager(Context context) {
        FlowManager.init(new FlowConfig.Builder(context).openDatabasesOnInit(true).build());
    }


    public void save(Photo photo) {
        try {
            photo.save();
        } catch (Exception e) {
            Log.w("SaveDog", e.toString());
        }
    }

    public List<Photo> getAll() {
        return SQLite.select()
                .from(Photo.class)
                .queryList();
    }

    public void delete(Photo photo) {
        photo.delete();
    }

    public Photo getByUrl(String url) {
        return SQLite.select()
                .from(Photo.class)
                .where(Photo_Table.url.eq(url))
                .querySingle();
    }

}

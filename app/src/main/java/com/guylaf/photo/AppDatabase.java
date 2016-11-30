package com.guylaf.photo;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by guyla on 30/11/2016.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, foreignKeysSupported = true)
public class AppDatabase {
    public static final String NAME = "Photo";

    public static final int VERSION = 2;
}

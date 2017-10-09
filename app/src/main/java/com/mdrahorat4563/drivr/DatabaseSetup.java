package com.mdrahorat4563.drivr;

import android.provider.BaseColumns;

/**
 * Created by micha on 10/4/2017.
 */

public final class DatabaseSetup implements BaseColumns{
    public static final String CAR_TABLE = "CarPics";
    public static final String LOGIN_TABLE = "Users";
    public static final String COLUMN_NAME_TITLE = "picture";



    public void CreateDatabase(){
        final String SQL_CREATE_CAR_TABLE =
            "CREATE TABLE " + DatabaseSetup.CAR_TABLE + " (" +
                DatabaseSetup._ID + " INTEGER PRIMARY KEY," +
                DatabaseSetup.COLUMN_NAME_TITLE + " TEXT)";

        final String SQL_CREATE_LOGIN_TABLE =
                "CREATE TABLE " + DatabaseSetup.LOGIN_TABLE + " (" +
                        DatabaseSetup._ID + " INTEGER PRIMARY KEY," +
                        DatabaseSetup.COLUMN_NAME_TITLE + " TEXT)";

        final String DROP_TABLES =
            "DROP TABLE IF EXISTS " + DatabaseSetup.CAR_TABLE;
    }
}

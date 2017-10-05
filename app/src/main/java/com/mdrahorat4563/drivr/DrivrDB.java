package com.mdrahorat4563.drivr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by micha on 10/4/2017.
 */

public final class DrivrDB {

    private DrivrDB() {}

    public static class DrivrDBEntry implements BaseColumns
    {
        public static final String LOGIN_TABLE = "Login";

        //Declare the columns
        public static final String LOGIN_ID = "_id";

        public static final String LOGIN_USERNAME= "username";

        public static final String LOGIN_PASSWORD = "password";
    }
}

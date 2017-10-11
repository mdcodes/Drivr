package com.mdrahorat4563.drivr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by micha on 10/4/2017.
 */

public final class DrivrDB {
    public static final String DB_NAME = "DrivrDB";
    public static final int DB_VERSION = 1;

    public static final String LOGIN_TABLE = "Login";

    //Declare the columns
    public static final String LOGIN_ID = "_id";
    public static final int LOGIN_ID_COL = 0;

    public static final String LOGIN_USERNAME= "username";
    public static final int LOGIN_NAME_COL = 1;

    public static final String LOGIN_PASSWORD = "password";
    public static final int LOGIN_PASSWORD_COL = 2;

    public static final String CREATE_DB =
            "CREATE DATABASE DrivrDB;";

    public static final String CREATE_TABLES =
            "CREATE TABLE " + LOGIN_TABLE + " (" +
                    LOGIN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    LOGIN_USERNAME + " TEXT NOT NULL UNIQUE," +
                    LOGIN_PASSWORD + " TEXT NOT NULL);";

    public void CreateDatabase(){

    }

    private class DBHelper extends SQLiteOpenHelper
    {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, name, factory, version);
        }

        public void onCreate(SQLiteDatabase db){
            db.execSQL(CREATE_DB);
            db.execSQL(CREATE_TABLES);

            db.execSQL("INSERT INTO Logins VALUES (1, 'abc@123.com', 'abc123');");
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.d("LoginDB", "Upgrading from v" + oldVersion + " to v" + newVersion);

            db.execSQL("DROP TABLE Logins;");
        }
    }
}

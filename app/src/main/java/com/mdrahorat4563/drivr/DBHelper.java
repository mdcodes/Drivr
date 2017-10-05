package com.mdrahorat4563.drivr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Michal Drahorat on 10/9/2017.
 */

public class DBHelper extends SQLiteOpenHelper
{

    public static final String DB_NAME = "DrivrDB";
    public static final int DB_VERSION = 1;

    public static final String CREATE_TABLES =
            "CREATE TABLE " + DrivrDB.DrivrDBEntry.LOGIN_TABLE + " (" +
                    DrivrDB.DrivrDBEntry.LOGIN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    DrivrDB.DrivrDBEntry.LOGIN_USERNAME + " TEXT NOT NULL UNIQUE," +
                    DrivrDB.DrivrDBEntry.LOGIN_PASSWORD + " TEXT NOT NULL);";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLES);

        db.execSQL("INSERT INTO Logins VALUES (1, 'abc@123.com', 'abc123');");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d("LoginDB", "Upgrading from v" + oldVersion + " to v" + newVersion);

        db.execSQL("DROP TABLE Logins;");
    }
}


package com.mdrahorat4563.drivr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.mdrahorat4563.drivr.DrivrDBContract.DrivrDBEntry;

import static com.mdrahorat4563.drivr.DrivrDBContract.DrivrDBEntry.LOGIN_TABLE_NAME;

/**
 * Created by Michal Drahorat on 10/9/2017.
 */

public class DrivrDBHelper extends SQLiteOpenHelper
{

    public static final String DB_NAME = "DrivrDB";
    public static final int DB_VERSION = 1;

    public static final String CREATE_LOGIN_TABLES =
            "CREATE TABLE " + LOGIN_TABLE_NAME + " (" +
                    DrivrDBEntry.LOGIN_ID_TITLE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DrivrDBEntry.LOGIN_USERNAME_TITLE + " TEXT NOT NULL UNIQUE," +
                    DrivrDBEntry.LOGIN_PASSWORD_TITLE + " TEXT NOT NULL);";

    public static final String CREATE_CAR_TABLES =
            "CREATE TABLE " + DrivrDBEntry.CARS_TABLE_NAME + " (" +
                    DrivrDBEntry.CAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DrivrDBEntry.CAR_MAKE + " TEXT NOT NULL," +
                    DrivrDBEntry.CAR_MODEL + " TEXT NOT NULL," +
                    DrivrDBEntry.CAR_YEAR + " TEXT NOT NULL,"+
                    DrivrDBEntry.CAR_PICTURE + "BLOB NOT NULL" +
                    DrivrDBEntry.CAR_OWNER_ID + " INTEGER, "  +
                    "FOREIGN KEY(ownerId) REFERENCES Login(_loginId));";

    public static final String CREATE_MESSAGES_TABLE =
            "CREATE TABLE " + DrivrDBEntry.MESSAGES_TABLE + " (" +
                    DrivrDBEntry.MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DrivrDBEntry.MESSAGE_SENDER_ID + "INTEGER, " +
                    DrivrDBEntry.MESSAGE_TEXT + " TEXT NOT NULL";

    public static final String DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LOGIN_TABLE_NAME;

    public DrivrDBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_LOGIN_TABLES);
        db.execSQL(CREATE_CAR_TABLES);
        db.execSQL(CREATE_MESSAGES_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d("LoginDB", "Upgrading from v" + oldVersion + " to v" + newVersion);

        db.execSQL(DELETE_ENTRIES);
    }

    public void addLogin(LoginModel login)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DrivrDBEntry.LOGIN_USERNAME_TITLE, login.getUserName());
        values.put(DrivrDBEntry.LOGIN_PASSWORD_TITLE, login.getPassword());

        db.insert(LOGIN_TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String userName){
        String[] projection = {
                DrivrDBEntry.LOGIN_USERNAME_TITLE
        };

        SQLiteDatabase db = this.getReadableDatabase();

        String selectuser = DrivrDBEntry.LOGIN_USERNAME_TITLE + " = ?";

        String[] selectionArgs = { userName };

        Cursor cursor = db.query(
                DrivrDBEntry.LOGIN_TABLE_NAME,
                projection,
                selectuser,
                selectionArgs,
                null,
                null,
                null
        );
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0)
        {
            return true;
        }
        return false;
    }
    public boolean checkUser(String userName, String password)
    {
        String[] projection = {
                DrivrDBEntry.LOGIN_USERNAME_TITLE,
                DrivrDBEntry.LOGIN_PASSWORD_TITLE
        };

        SQLiteDatabase db = this.getReadableDatabase();

        String selectuser = DrivrDBEntry.LOGIN_USERNAME_TITLE + " = ?" + " AND " +
                DrivrDBEntry.LOGIN_PASSWORD_TITLE + " = ?";

        String[] selectionArgs = { userName, password};

        Cursor cursor = db.query(
                DrivrDBEntry.LOGIN_TABLE_NAME,
                projection,
                selectuser,
                selectionArgs,
                null,
                null,
                null
        );
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0)
        {
            return true;
        }
        return false;
    }
}


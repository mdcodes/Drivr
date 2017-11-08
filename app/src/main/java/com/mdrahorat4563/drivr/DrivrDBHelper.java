package com.mdrahorat4563.drivr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.mdrahorat4563.drivr.DrivrDBContract.DrivrDBEntry;
import com.mdrahorat4563.drivr.Models.CarManufacturerModel;
import com.mdrahorat4563.drivr.Models.LoginModel;
import com.mdrahorat4563.drivr.Models.PostsModel;

import java.util.ArrayList;
import java.util.List;

import static com.mdrahorat4563.drivr.DrivrDBContract.DrivrDBEntry.FORUM_TABLE;
import static com.mdrahorat4563.drivr.DrivrDBContract.DrivrDBEntry.LOGIN_ID_TITLE;
import static com.mdrahorat4563.drivr.DrivrDBContract.DrivrDBEntry.MANUFACTURER_TABLE;
import static com.mdrahorat4563.drivr.DrivrDBContract.DrivrDBEntry.MANUFACTURER_ID;
import static com.mdrahorat4563.drivr.DrivrDBContract.DrivrDBEntry.MANUFACTURER_NAME;
import static com.mdrahorat4563.drivr.DrivrDBContract.DrivrDBEntry.MANUFACTURER_LOGO;
import static com.mdrahorat4563.drivr.DrivrDBContract.DrivrDBEntry.LOGIN_TABLE;
import static com.mdrahorat4563.drivr.DrivrDBContract.DrivrDBEntry.MEMBER_FORUM_TABLE;
import static com.mdrahorat4563.drivr.DrivrDBContract.DrivrDBEntry.POSTS_TABLE;

/**
 * Created by Michal Drahorat on 10/9/2017.
 */

public class DrivrDBHelper extends SQLiteOpenHelper
{

    private SQLiteDatabase mDb;

    public static final String DB_NAME = "DrivrDB";
    public static final int DB_VERSION = 2;

    public static final String CREATE_LOGIN_TABLES =
            "CREATE TABLE " + LOGIN_TABLE + " (" +
                    DrivrDBEntry.LOGIN_ID_TITLE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DrivrDBEntry.LOGIN_USERNAME_TITLE + " TEXT NOT NULL UNIQUE," +
                    DrivrDBEntry.LOGIN_PASSWORD_TITLE + " TEXT NOT NULL);";

    public static final String CREATE_MANUFACTURER_TABLES =
            "CREATE TABLE " + DrivrDBEntry.MANUFACTURER_TABLE + " (" +
                    DrivrDBEntry.MANUFACTURER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DrivrDBEntry.MANUFACTURER_NAME + " TEXT NOT NULL," +
                    DrivrDBEntry.MANUFACTURER_LOGO + " BLOB);";

    public static final String CREATE_FORUMS_TABLE =
            "CREATE TABLE " + DrivrDBEntry.FORUM_TABLE + " (" +
                    DrivrDBEntry.FORUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DrivrDBEntry.FORUM_NAME + " TEXT NOT NULL, " +
                    DrivrDBEntry.FORUM_MANUFACTURER_ID + " INTEGER, " +
                    "FOREIGN KEY (manId) REFERENCES Manufacturers(manId));";

    public static final String CREATE_POSTS_TABLE =
            "CREATE TABLE " + DrivrDBEntry.POSTS_TABLE + " (" +
                    DrivrDBEntry.POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DrivrDBEntry.POST_TEXT + " TEXT NOT NULL, " +
                    DrivrDBEntry.POST_FORUM_ID + " INTEGER, " +
                    DrivrDBEntry.POST_AUTHOR_ID + " INTEGER, " +
                    " FOREIGN KEY(authorId) REFERENCES Logins(loginId)" +
                    " FOREIGN KEY(forumId) REFERENCES Forums(forumId));";

    public static final String CREATE_MEMBER_FORUM_TABLE =
            "CREATE TABLE " + DrivrDBEntry.MEMBER_FORUM_TABLE + " (" +
                    DrivrDBEntry.M_FORUM_ID + " INTEGER NOT NULL, " +
                    DrivrDBEntry.M_MEMBER_ID + " INTEGER NOT NULL, " +
                    DrivrDBEntry.M_FORUM_NAME + " STRING, " +
                    "FOREIGN KEY(forumId) REFERENCES Forums(forumId), " +
                    "FOREIGN KEY (memberId) REFERENCES Logins(loginId));";


    public static final String INSERT_MANUFACTURERS =
            "INSERT INTO " + DrivrDBEntry.MANUFACTURER_TABLE + " (" +
                    DrivrDBEntry.MANUFACTURER_NAME + ") " +
                    "VALUES" +
                    "('Honda')," +
                    "('Acura')," +
                    "('Toyota')," +
                    "('Dodge')," +
                    "('Lamborghini')," +
                    "('Ferrari');";

    public static final String DELETE_LOGIN_ENTRIES =
            "DROP TABLE IF EXISTS " + LOGIN_TABLE;

    public static final String DELETE_MANUFACTURER_ENTRIES =
            "DROP TABLE IF EXISTS " + MANUFACTURER_TABLE;

    public static final String DELETE_POSTS_ENTRIES =
            "DROP TABLE IF EXISTS " + POSTS_TABLE;

    public static final String DELETE_FORUMS =
            "DROP TABLE IF EXISTS " + FORUM_TABLE;

    public static final String DELETE_MEMBER_FORUM =
            "DROP TABLE IF EXISTS " + MEMBER_FORUM_TABLE;

    public DrivrDBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_LOGIN_TABLES);
        db.execSQL(CREATE_MANUFACTURER_TABLES);
        db.execSQL(CREATE_FORUMS_TABLE);
        db.execSQL(CREATE_POSTS_TABLE);
        db.execSQL(CREATE_MEMBER_FORUM_TABLE);
        db.execSQL(INSERT_MANUFACTURERS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d("LoginDB", "Upgrading from v" + oldVersion + " to v" + newVersion);

        db.execSQL(DELETE_LOGIN_ENTRIES);
        db.execSQL(DELETE_MANUFACTURER_ENTRIES);
        db.execSQL(DELETE_FORUMS);
        db.execSQL(DELETE_POSTS_ENTRIES);
        db.execSQL(DELETE_MEMBER_FORUM);
    }

    public void addLogin(LoginModel login)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DrivrDBEntry.LOGIN_USERNAME_TITLE, login.getUserName());
        values.put(DrivrDBEntry.LOGIN_PASSWORD_TITLE, login.getPassword());

        db.insert(LOGIN_TABLE, null, values);
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
                DrivrDBEntry.LOGIN_TABLE,
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
                DrivrDBEntry.LOGIN_TABLE,
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

    public void addPost(PostsModel post){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DrivrDBEntry.POST_TEXT, post.getPostText());
        values.put(DrivrDBEntry.POST_FORUM_ID, post.getForumId());
        values.put(DrivrDBEntry.POST_AUTHOR_ID, post.getAuthorId());

        db.insert(POSTS_TABLE, null, values);
        db.close();
    }

    public void deletePost(int id){

        SQLiteDatabase db = getWritableDatabase();

        String selection = DrivrDBEntry.POST_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        db.delete(DrivrDBEntry.POSTS_TABLE, selection, selectionArgs);
    }

    public int getCurrentAvailablePostId(){
        int nextAvailable = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        String[] queryParams = {
                "MAX(" + DrivrDBEntry.POST_ID + ")"
        };

        Cursor c = db.query(
                DrivrDBEntry.POSTS_TABLE,
                queryParams,
                null,
                null,
                null,
                null,
                null
        );
        if (c.moveToFirst()){
            do{
                nextAvailable = c.getInt(0);
            }while(c.moveToNext());
        }

        c.close();
        return nextAvailable;
    }

    public int getCurrentUserId(Context context){

        int userId = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
          DrivrDBEntry.LOGIN_ID_TITLE
        };

        String whereClause = DrivrDBEntry.LOGIN_USERNAME_TITLE + " = ?";
        String[] selectionArgs = {SaveSharedPreference.getUserName(context)};

        Cursor c = db.query(
                LOGIN_TABLE,
                projection,
                whereClause,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()){
            do
            {
                userId = c.getInt(0);
            }while(c.moveToNext());
        }

        return userId;
    }
}


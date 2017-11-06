package com.mdrahorat4563.drivr.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mdrahorat4563.drivr.DrivrDBContract.*;
import com.mdrahorat4563.drivr.DrivrDBHelper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal Drahorat on 10/22/2017.
 */

public class ForumModel {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public ArrayList<String> getAllForumNames(Context context){
        final ArrayList<String> forumList = new ArrayList<>();

        DrivrDBHelper dbh = new DrivrDBHelper(context);

        SQLiteDatabase db = dbh.getReadableDatabase();

        String[] projection = {
                DrivrDBEntry.MANUFACTURER_NAME
        };

        Cursor cursor = db.query(
                DrivrDBEntry.MANUFACTURER_TABLE,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()){
            do
            {
                forumList.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return forumList;
    }

    public ForumModel(){}

    public ForumModel(int id, int manufacturerId, String forumName) {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.forumName = forumName;
    }

    private int id;
    private int manufacturerId;
    private String forumName;

    List<ForumModel> modelPics = new ArrayList<ForumModel>();

}

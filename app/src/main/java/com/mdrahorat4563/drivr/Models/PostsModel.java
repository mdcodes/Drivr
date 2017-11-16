package com.mdrahorat4563.drivr.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mdrahorat4563.drivr.DrivrDBContract;
import com.mdrahorat4563.drivr.DrivrDBHelper;
import com.mdrahorat4563.drivr.DrivrDBContract.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michal Drahorat on 10/18/2017.
 */

public class PostsModel {
    private int postId;
    private String postText;
    private int forumId;
    private int authorId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public PostsModel(){}

    public PostsModel(int postId, String postText, int forumId, int authorId) {
        this.postId = postId;
        this.postText = postText;
        this.forumId = forumId;
        this.authorId = authorId;
    }

    public ArrayList<String> getPostsForCertainForum(Context context, int forumId){
        final ArrayList<String> postList = new ArrayList<>();

        DrivrDBHelper dbh = new DrivrDBHelper(context);

        SQLiteDatabase db = dbh.getReadableDatabase();

        String[] projection = {
                DrivrDBEntry.POST_ID,
                DrivrDBEntry.POST_TEXT
        };

        String selection = DrivrDBEntry.POST_FORUM_ID + " = ?";

        String[] selectionArgs = {
               String.valueOf(forumId)
        };

        Cursor cursor = db.query(
                DrivrDBEntry.POSTS_TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()){
            do{
                postList.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }
        cursor.close();

        return postList;
    }

    public List<PostsModel> posts = new ArrayList<PostsModel>() ;
}

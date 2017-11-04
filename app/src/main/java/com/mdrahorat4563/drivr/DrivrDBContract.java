package com.mdrahorat4563.drivr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by micha on 10/4/2017.
 */

public final class DrivrDBContract {

    private DrivrDBContract() {}

    public static class DrivrDBEntry implements BaseColumns
    {
        //Table Names
        public static final String LOGIN_TABLE = "Logins";

        public static final String MANUFACTURER_TABLE = "Manufacturers";

        public static final String POSTS_TABLE = "Posts";

        public static final String FORUM_TABLE = "Forums";

        public static final String MEMBER_FORUM_TABLE = "Forums";


        //Login Table Columns
        public static final String LOGIN_ID_TITLE = "loginId";

        public static final String LOGIN_USERNAME_TITLE = "Username";

        public static final String LOGIN_PASSWORD_TITLE = "Password";

        //Manufacturer table columns
        public static final String MANUFACTURER_ID = "manId";
        public static final String MANUFACTURER_NAME = "Name";
        public static final String MANUFACTURER_LOGO = "Logo";

        //Posts table columns
        public static final String POST_ID = "carId";
        public static final String POST_TEXT = "Post Text";
        public static final String POST_FORUM_ID = "forumId";
        public static final String POST_AUTHOR_ID = "authorId";

        //Forum table columns
        public static final String FORUM_ID = "forumId";
        public static final String FORUM_NAME = "Name";
        public static final String FORUM_MANUFACTURER_ID = "manId";

        //Member Forum Table Columns
        public static final String M_MEMBER_ID = "memberId";
        public static final String M_FORUM_ID = "forumId";
        public static final String M_FORUM_NAME = "name";
    }
}

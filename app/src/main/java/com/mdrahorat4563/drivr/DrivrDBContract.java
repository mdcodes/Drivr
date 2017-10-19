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
        public static final String LOGIN_TABLE_NAME = "Login";

        public static final String CARS_TABLE_NAME = "Cars";

        public static final String MESSAGES_TABLE = "Messages";

        //Login Table Columns
        public static final String LOGIN_ID_TITLE = "_loginid";

        public static final String LOGIN_USERNAME_TITLE = "username";

        public static final String LOGIN_PASSWORD_TITLE = "password";

        //Car table columns
        public static final String CAR_ID = "_carid";
        public static final String CAR_MAKE = "Make";
        public static final String CAR_MODEL = "Make";
        public static final String CAR_YEAR = "Make";
        public static final String CAR_PICTURE = "Picture";
        public static final String CAR_OWNER_ID = "ownerId";

        //Messages table columns
        public static final String MESSAGE_ID = "_carid";
        public static final String MESSAGE_TEXT = "Message";
        public static final String MESSAGE_SENDER_ID = "senderId";
    }
}

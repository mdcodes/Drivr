package com.mdrahorat4563.drivr;

import android.provider.BaseColumns;

/**
 * Created by micha on 10/4/2017.
 */

public final class DatabaseSetup {
    private DatabaseSetup(){}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "CarPics";
        public static final String COLUMN_NAME_TITLE = "picture";
    }
}

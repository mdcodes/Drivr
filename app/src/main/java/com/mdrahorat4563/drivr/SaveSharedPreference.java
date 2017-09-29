package com.mdrahorat4563.drivr;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by micha on 9/28/2017.
 */

public class SaveSharedPreference {
    static final String PREF_USER_NAME = "username";
    static final String PREF_PASSWORD = "password";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserNameAndPassword(Context ctx, String userName, String password){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.putString(PREF_PASSWORD, password);
        editor.apply();
        editor.commit();
    }

    public static String getUserName(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getPassword(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_PASSWORD, "");
    }

    public static void deleteSharedPrefs(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.apply();
    }
}

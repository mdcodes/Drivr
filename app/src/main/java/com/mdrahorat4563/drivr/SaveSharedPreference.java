/*
* Name: Michal Drahorat
* Description: The SaveSharedPreferences class for the Drivr app.
* */

package com.mdrahorat4563.drivr;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by micha on 9/28/2017.
 *
 *
 */
public class SaveSharedPreference {
    static final String PREF_USER_NAME = "username";
    static final String PREF_PASSWORD = "password";
    static final String CURRENT_ID = "currentId";

    /**
     *Gets the SharedPreferences from context
     * @param ctx - The application context
     * @return - Returns the defaults for the Shared Preferences
     */
    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    /**
     *Sets both the username and password as SharedPreference objects
     * @param ctx - The application context
     * @param userName - The username to be stored
     * @param password - The password to be stored
     */
    public static void setUserNameAndPassword(Context ctx, String userName, String password){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.putString(PREF_PASSWORD, password);
        editor.apply();
        editor.commit();
    }

    public static void setCurrentId(Context ctx, int id){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(CURRENT_ID, id);

        editor.apply();
        editor.commit();
    }

    /**
     * Gets the username parameter from the SharedPreferences
     * @param ctx - The application context
     * @return
     */
    public static String getUserName(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    /**
     * gets the password parameter from the SharedPreferences
     * @param ctx - The application context
     * @return
     */
    public static String getPassword(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_PASSWORD, "");
    }

    /**
     * Clears out the SharedPreferences
     * @param ctx - The application context
     */
    /*TODO: Implement a logout function which will use this method*/
    public static void deleteSharedPrefs(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.apply();
    }
}

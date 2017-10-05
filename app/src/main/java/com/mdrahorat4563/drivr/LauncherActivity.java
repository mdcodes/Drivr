/*
* Name: Michal Drahorat
* Description: The LauncherActivity class for the Drivr app.
* */

package com.mdrahorat4563.drivr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);

//        mVisible = true;
//        mControlsView = findViewById(R.id.fullscreen_content_controls);


        new Timer().schedule(new TimerTask() {
            public void run() {
                if (SaveSharedPreference.getUserName(LauncherActivity.this).length() == 0 && SaveSharedPreference.getPassword(LauncherActivity.this).length() == 0){
                    startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
                }
                else{
                    startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                }
            }
        }, 3000);
    }
}

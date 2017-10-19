/*
* Name: Michal Drahorat
* Description: The MainActivity class for the Drivr app.
* */

package com.mdrahorat4563.drivr;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    List<ImageView> carPics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton likeButton = (ImageButton) findViewById(R.id.button_like);
    }

    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);

        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.show();
    }

    public boolean onMenuItemClick(MenuItem item){
        switch(item.getItemId()){
            case R.id.messages:
                return true;
            case R.id.logout:
                SaveSharedPreference.deleteSharedPrefs(getApplicationContext());
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            default:
                return false;
        }
    }

    public void likeButtonClick(){

    }

    public void dislikeButtonClick(){

    }
}

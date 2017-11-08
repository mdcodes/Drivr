/*
* Name: Michal Drahorat
* Description: The MainActivity class for the Drivr app.
* */

package com.mdrahorat4563.drivr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.List;

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
        Intent intent;
        switch(item.getItemId()){
            case R.id.forumList:
                intent = new Intent(MainActivity.this, ForumActivity.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                SaveSharedPreference.deleteSharedPrefs(getApplicationContext());
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            default:
                return false;
        }
    }

    public void likeButtonClick(){
    }

    public void dislikeButtonClick(){

    }

    public void populateImageView(){

    }
}

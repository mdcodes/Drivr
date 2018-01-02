package com.mdrahorat4563.drivr;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.mdrahorat4563.drivr.Models.ForumModel;
import com.mdrahorat4563.drivr.Models.PostsModel;

import java.util.ArrayList;

public class ForumActivity extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener{
    ForumModel fm = new ForumModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        final Context context = getApplicationContext();

        final ListView lv = (ListView) findViewById(R.id.forumNameListView);

        final ArrayList<String> list = fm.getAllForumNames(context);

        final StableArrayAdapter adapter =
                new StableArrayAdapter(context, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Intent intent = new Intent(ForumActivity.this, PostActivity.class);
                intent.putExtra("forumId", position);
                startActivity(intent);
            }
        });
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);

        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String[] emailList = {"mdrahorat4563@conestogac.on.ca"};
        Intent i;
        switch (item.getItemId()) {
            case R.id.contact:
                i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL, emailList);
                i.putExtra(Intent.EXTRA_SUBJECT, "Contact Us!");
                startActivity(Intent.createChooser(i, "Choose an app to handle the request..."));
                return true;
            case R.id.logout:
                return true;
            case R.id.viewImage:
                i = new Intent(ForumActivity.this, ImageActivity.class);
                startActivity(i);
                return true;
            default:
                return false;
        }
    }
}

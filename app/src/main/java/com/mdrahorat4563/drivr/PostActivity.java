package com.mdrahorat4563.drivr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.mdrahorat4563.drivr.Models.LoginModel;
import com.mdrahorat4563.drivr.Models.PostsModel;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {
    PostsModel pm = new PostsModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Bundle extras = getIntent().getExtras();

        final Context context = getApplicationContext();

        final ListView lv = (ListView) findViewById(R.id.postListView);

        final ArrayList<String> list = pm.getPostsForCertainForum(context, extras.getLong("forumId"));

        final StableArrayAdapter adapter =
                new StableArrayAdapter(context, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrivrDBHelper dbh = new DrivrDBHelper(context);

                Intent intent = new Intent(PostActivity.this, AddPostActivity.class);
                int userId = (dbh.getCurrentUserId(context));
                long forumId = extras.getLong("forumId");
                intent.putExtra("forumId", forumId);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
    }

}

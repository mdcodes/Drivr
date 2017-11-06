package com.mdrahorat4563.drivr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mdrahorat4563.drivr.Models.ForumModel;
import com.mdrahorat4563.drivr.Models.PostsModel;

import java.util.ArrayList;

public class ForumActivity extends AppCompatActivity {
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
}

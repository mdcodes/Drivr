package com.mdrahorat4563.drivr;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.*;
import android.widget.ListView;

import com.mdrahorat4563.drivr.Models.LoginModel;
import com.mdrahorat4563.drivr.Models.PostsModel;

import java.util.ArrayList;
import java.util.HashMap;

public class PostActivity extends ListActivity {
    PostsModel pm = new PostsModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StableArrayAdapter2 adapter2 = new StableArrayAdapter2(this, loadList());

        loadList();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadList();
    }


    public ArrayList<PostsModel> loadList(){
        final Bundle extras = getIntent().getExtras();

        final Context context = getApplicationContext();

        final ListView lv = (ListView) findViewById(R.id.postListView);

        final ArrayList<String> list = pm.getPostsForCertainForum(context, extras.getInt("forumId"));

        registerForContextMenu(lv);

        final StableArrayAdapter adapter =
                new StableArrayAdapter(context, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

        registerForContextMenu(lv);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrivrDBHelper dbh = new DrivrDBHelper(context);

                Intent intent = new Intent(PostActivity.this, AddPostActivity.class);
                int userId = (dbh.getCurrentUserId(context));
                int forumId = extras.getInt("forumId");
                intent.putExtra("forumId", forumId);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        return null;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        String MENU_DELETE = "Delete";

        if (v.getId() == R.id.postListView){
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            String postString = (String) lv.getItemAtPosition(acmi.position);

            menu.add("Delete");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ListView lv = (ListView) findViewById(R.id.postListView);
        Context ctx = getApplicationContext();
        DrivrDBHelper dbh = new DrivrDBHelper(ctx);
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        int postId = lv.getSelectedItemPosition();
        switch (item.getItemId()) {
            case 0:
                dbh.deletePost(postId);
                loadList();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}

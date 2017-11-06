package com.mdrahorat4563.drivr;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mdrahorat4563.drivr.Models.LoginModel;
import com.mdrahorat4563.drivr.Models.PostsModel;

public class AddPostActivity extends AppCompatActivity {
    DrivrDBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        final Context context = getApplicationContext();

        final Bundle extras = getIntent().getExtras();

        initObjects();

        final EditText postText = (EditText) findViewById(R.id.postText);

        ImageButton addPostButton = (ImageButton) findViewById(R.id.addPostButton);

        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (postText.getText().toString().equals("")){
                    Snackbar sb = Snackbar.make(v,
                            "Post text cannot be null.", Snackbar.LENGTH_LONG);
                    sb.show();
                }
                else
                {
                    int postId = dbh.getCurrentAvailablePostId();
                    int forumId = extras.getInt("forumId");
                    int authorId = extras.getInt("userId");
                    String text = postText.getText().toString();

                    PostsModel post = new PostsModel(
                            postId,
                            text,
                            forumId,
                            authorId);
                    dbh.addPost(post);

                    Snackbar sb = Snackbar.make(v, "Post successfully created.",
                            Snackbar.LENGTH_SHORT);
                    sb.show();

                    Intent intent = new Intent(AddPostActivity.this, PostActivity.class);
                    int userId = (dbh.getCurrentUserId(context));
                    intent.putExtra("forumId", forumId);
                    intent.putExtra("userId", userId);
                    finish();
                    startActivity(intent);

                }
            }
        });


    }

    public void initObjects(){
        dbh = new DrivrDBHelper(getApplicationContext());
    }
}

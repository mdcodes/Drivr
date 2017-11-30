package com.mdrahorat4563.drivr;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.mdrahorat4563.drivr.Models.PostsModel;

import java.util.ArrayList;

/**
 * Created by mdrahorat4563 on 11/29/2017.
 */

public class CustomAdapter extends ArrayAdapter<PostsModel>{

    private ArrayList<PostsModel> postSet;
    Context context;

    public CustomAdapter(ArrayList<PostsModel> posts, Context context){
        super(context, R.layout.row_item, posts);
        this.postSet = posts;
        this.context = context;
    };
}

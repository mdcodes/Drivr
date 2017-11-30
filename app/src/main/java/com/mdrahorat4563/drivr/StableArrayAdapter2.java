package com.mdrahorat4563.drivr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mdrahorat4563.drivr.Models.PostsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michal Drahorat on 10/26/2017.
 */

public class StableArrayAdapter2 extends ArrayAdapter<PostsModel> {
    private final Context context;
    private final ArrayList<PostsModel> objects;
    public StableArrayAdapter2(Context context, ArrayList<PostsModel> objects){
        super(context, R.layout.row_item, objects);

        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.
                LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_item, parent, false);

        TextView valueView = (TextView) rowView.findViewById(R.id.txtPostText);

        valueView.setText(objects.get(position).getPostText());

        return rowView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

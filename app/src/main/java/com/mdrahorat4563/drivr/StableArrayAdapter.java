package com.mdrahorat4563.drivr;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Michal Drahorat on 10/26/2017.
 */

public class StableArrayAdapter extends ArrayAdapter<String> {
    HashMap<String, Integer> idMap = new HashMap<>();
    public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects){
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            idMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position){
        String item = getItem(position);
        return idMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

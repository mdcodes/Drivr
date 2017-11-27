package com.mdrahorat4563.drivr;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Michal Drahorat on 11/20/2017.
 */

public class StableHashMapAdapter extends ArrayAdapter<HashMap<Integer, String>> {
    HashMap<Integer, String> idMap = new HashMap<>();
    public StableHashMapAdapter(Context context, int textViewResourceId, List<HashMap<Integer, String>> objects){
        super(context, textViewResourceId, objects);
        for (int i = 1; i < objects.size(); ++i) {
            idMap.put(i, objects.toString());
        }
    }

    @Override
    public long getItemId(int position){
        HashMap<Integer, String> item = getItem(position);
        return idMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

package com.mdrahorat4563.drivr;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Michal Drahorat on 10/26/2017.
 */

public class StableArrayAdapter extends ArrayAdapter<String> {
    public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects){
        super(context, textViewResourceId, objects);
    }
}

package com.mdrahorat4563.drivr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    List<CarPicsModel> carsList = new ArrayList<CarPicsModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }
}

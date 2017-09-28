package com.mdrahorat4563.drivr;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView picture;
    List<Image> images = new List<Image>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picture = (ImageView) findViewById(R.id.imageView);

    }
}

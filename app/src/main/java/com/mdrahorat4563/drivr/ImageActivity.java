package com.mdrahorat4563.drivr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.squareup.picasso.Picasso;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageActivity extends AppCompatActivity {
    private ImageDownload backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        backgroundTask = new ImageDownload();


        backgroundTask.execute("https://i.imgur.com/sbfmufg.jpg");
    }


    class ImageDownload extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap myBitmap = null;
            try
            {
                InputStream in = new java.net.URL(urldisplay).openStream();
                myBitmap = BitmapFactory.decodeStream(in);

            }catch (IOException e){
                Log.e("IOException", "Encountered an IOException");
            }


            return myBitmap;
        }

        protected void onPostExecute(Bitmap result) {
            ImageView img = (ImageView) findViewById(R.id.downloadedImage);
            img.setImageBitmap(result);
        }
    }
}

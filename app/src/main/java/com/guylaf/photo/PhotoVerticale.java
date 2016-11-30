package com.guylaf.photo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.guylaf.photo.Photo;
import com.guylaf.photos.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhotoVerticale extends AppCompatActivity {
    final List<Photo> list1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoverticale);
        String title = getIntent().getStringExtra(ImageActivity.titleVertical);
        String url = getIntent().getStringExtra(ImageActivity.urlVertical);
        TextView displayTitle = (TextView) findViewById(R.id.titleVerticale);
        displayTitle.setText(title);
        TextView displayUrl = (TextView) findViewById(R.id.URLVerticale);
        displayUrl.setText(url);
        ImageView image = (ImageView) findViewById(R.id.imgVerticale);
        Picasso.with(this).load(url).into(image);
    }
}

package com.guylaf.photo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhotoVerticale extends AppCompatActivity {
    final List<Photo> list1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoverticale);
        String title = getIntent().getStringExtra("Title");
        String url = getIntent().getStringExtra("URL");
        TextView displayTitle = (TextView) findViewById(R.id.titleVerticale);
        displayTitle.setText(title);
        TextView displayUrl = (TextView) findViewById(R.id.URLVerticale);
        displayUrl.setText(url);
        ImageView image = (ImageView) findViewById(R.id.imgVerticale);
        Picasso.with(this).load(url).into(image);
    }
}

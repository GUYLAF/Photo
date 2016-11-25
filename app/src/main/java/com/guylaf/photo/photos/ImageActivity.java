package com.guylaf.photo.photos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.guylaf.photos.R;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        final List<Photo> list = new ArrayList<>();

        list.add(new Photo("Title1", "http://www.ecobase21.net/Paysage/Liens/Paysagesnaturels5.jpg"));
        list.add(new Photo("Title2", "http://www.unesourisetmoi.info/wallpaper_24/images/paysages-de-reve-06.jpg"));
        list.add(new Photo("Title3", "http://testeurs.callandgo.fr/wp-content/uploads/Paysage-de-neige-13.jpg"));

        final ListView listView = (ListView) findViewById(R.id.list);
        final ImageAdapter imageAdapter = new ImageAdapter(this);
        listView.setAdapter(imageAdapter);
        imageAdapter.setList(list);
        imageAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Photo photo = imageAdapter.getItem(position);
                String title = photo.getTitle();
                String url = photo.getUrl();
                Intent intent = new Intent(ImageActivity.this, PhotoVerticale.class);
                intent.putExtra("Title",title);
                intent.putExtra("URL",url);
                startActivity(intent);
            }
        });

    }
}


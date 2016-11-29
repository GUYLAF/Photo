package com.guylaf.photo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.guylaf.photos.R;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity implements InterfaceResponse {

    private BoundService boundService;
    boolean bound = false;
    private List<Photo> list = new ArrayList<>();
    private List<Photo> listOne = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
//                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                String mTitle = "Title";
                getSupportActionBar().setTitle(mTitle);
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                String mDrawerTitle = "MyDrawer";
                getSupportActionBar().setTitle(mDrawerTitle);
            }
        };
// Set the drawer toggle as the DrawerListener

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        final ListView listView = (ListView) findViewById(R.id.list);
        imageAdapter = new ImageAdapter(this);
        listView.setAdapter(imageAdapter);

        final EditText editText = (EditText) findViewById(R.id.text_field);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Photo photo = imageAdapter.getItem(position);
                String title = photo.getTitle();
                String url = photo.getUrl();
                Intent intent = new Intent(ImageActivity.this, PhotoVerticale.class);
                intent.putExtra("Title", title);
                intent.putExtra("URL", url);
                startActivity(intent);
            }
        });

        Button button = (Button) findViewById(R.id.button_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")) {
                    Toast.makeText(ImageActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                listOne.clear();
                list.clear();
                boundService.getPhoto(editText.getText().toString());
                final String photoSearch = editText.getText().toString();

                for (int i = 0; i < list.size(); i++) {
                    Photo photo = list.get(i);
                    String title = list.get(i).getTitle();
                    if (title.equals(photoSearch)) {
                        listOne.add(new Photo(photo.getTitle(), photo.getUrl()));
                        imageAdapter.setList(listOne);

                        break;
                    } else {
                        imageAdapter.setList(list);
                    }
                }
                imageAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(mConnection);
            bound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            BoundService.ServiceBinder binder = (BoundService.ServiceBinder) service;
            boundService = binder.getService();
            bound = true;
            boundService.setInterfaceResponse(ImageActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    @Override
    public void onPhotoReceived(List<Photo> photos) {
        imageAdapter.setList(photos);
        imageAdapter.notifyDataSetChanged();
    }
}





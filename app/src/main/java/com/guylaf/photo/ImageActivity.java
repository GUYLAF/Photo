package com.guylaf.photo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.guylaf.photos.R;

import io.fabric.sdk.android.Fabric;

import java.util.ArrayList;
import java.util.List;


public class ImageActivity extends AppCompatActivity implements InterfaceResponse {

    public static final String spinnerKey = "key";
    public static final String titleVertical = "Title";
    public static final String urlVertical = "URL";
    private static SharedPreferences getPr;
    boolean bound = false;
    ActionBarDrawerToggle mDrawerToggle;
    PhotoPersistenceManager photoPers;
    private BoundService boundService;
    private List<Photo> list = new ArrayList<>();
    private List<Photo> listOne = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private DrawerLayout mDrawerLayout;
    private SharedPreferences prefs;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private com.google.android.gms.common.api.GoogleApiClient client;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity);
        Drawer();
        spinner();
        getButtonSearch();
        getButtonHistory();
        final EditText editText = listView();
        buttonOK(editText);
        photoPers = new PhotoPersistenceManager(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    // Fin onCReate **************************************************************************


    private void buttonOK(final EditText editText) {
        Button button = (Button) findViewById(R.id.button_ok);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if (!editText.getText().toString().equals("")) {
//                                        Toast.makeText(ImageActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                                              Toast.makeText(ImageActivity.this, prefs.getString(spinnerKey, "5"), Toast.LENGTH_SHORT).show();
                                          }

                                          listOne.clear();
                                          list.clear();
                                          boundService.getPhoto(editText.getText().toString(), prefs.getString(spinnerKey, "5"));
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
                                  }

        );
    }

    private EditText listView() {
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
                                                Photo photo1 = photoPers.getByUrl(url);
                                                if (photo1 == null) {
                                                    photoPers.save(photo);
                                                }
                                                Intent intent = new Intent(ImageActivity.this, PhotoVerticale.class);
                                                intent.putExtra(titleVertical, title);
                                                intent.putExtra(urlVertical, url);
                                                startActivity(intent);
                                            }
                                        }

        );
        return editText;
    }


    private void getButtonSearch() {
        Button buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View v) {
                                                LinearLayout searchLayout = (LinearLayout) findViewById(R.id.searchLayout);
                                                searchLayout.setVisibility(View.VISIBLE);
                                                mDrawerLayout.closeDrawers();
                                                imageAdapter.setList(new ArrayList<Photo>());
                                                imageAdapter.notifyDataSetChanged();
                                                imageAdapter.setHisto(false);
//                                                LinearLayout imageLayout = (LinearLayout) findViewById(R.id.activity_image);
//                                                searchLayout.setVisibility(View.VISIBLE);
//                                                LinearLayout drawerLayout = (LinearLayout) findViewById(R.id.left_drawer);
//                                                drawerLayout.setVisibility(View.INVISIBLE);
                                            }
                                        }
        );

    }

    private void getButtonHistory() {
        final Button buttonHistory = (Button) findViewById(R.id.button_history);
        buttonHistory.setOnClickListener(new View.OnClickListener() {

                                             @Override
                                             public void onClick(View v) {
                                                 LinearLayout searchLayout = (LinearLayout) findViewById(R.id.searchLayout);
                                                 searchLayout.setVisibility(View.GONE);
                                                 imageAdapter.setList(photoPers.getAll());
                                                 imageAdapter.notifyDataSetChanged();
                                                 mDrawerLayout.closeDrawers();
                                                 imageAdapter.setHisto(true);
//                                                 SharedPreferences.Editor editor = prefs.edit();
//                                                 editor.putString(, adapter.getItem(position).toString());
//                                                 editor.commit();
                                             }
                                         }
        );
    }


    private void Drawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
//                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {
        };


        mDrawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void spinner() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(spinnerKey, adapter.getItem(position).toString());
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        prefs = this.getPreferences(MODE_PRIVATE);

        String getPrefs = prefs.getString(spinnerKey, "5");
        spinner.setSelection(adapter.getPosition(getPrefs));
    }

    @Override
    protected void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        if (bound) {
            unbindService(mConnection);
            bound = false;
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    public void onPhotoReceived(List<Photo> photos) {
        imageAdapter.setList(photos);
        imageAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Image Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }


}





package com.guylaf.photo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.guylaf.photos.R;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by guyla on 25/11/2016.
 */

public class BoundService extends Service {

    private final IBinder binder = new ServiceBinder();
    private final String URL = "https://www.flickr.com" ;
    private FlickrService service;
    private InterfaceResponse interfaceResponse;

    public void setInterfaceResponse(InterfaceResponse interfaceResponse) {
        this.interfaceResponse = interfaceResponse;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(FlickrService.class);
        return binder;
    }

    public class ServiceBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }

    public void getPhoto(String query,String flickrPerPage) {
//        List<PhotoDto> listphotos = new ArrayList<>();
//        listphotos.add(new PhotoDto("28087377815", "67221971@N06", "80c8f2e753", "7308", 8, "t1", 1, 0, 0));//Monde Gris
//        listphotos.add(new PhotoDto("27425564203", "67221971@N06", "8204d2863d", "7303", 8, "t2", 1, 0, 0));//L'Arbre Blanc
//        listphotos.add(new PhotoDto("24077385539", "67221971@N06", "7b04e4d47a", "1444", 2, "t3", 1, 0, 0));//Les couleurs du vent
//
//        FlickrPhotosDto photos = new FlickrPhotosDto(1, 30, 5, "147", listphotos);
//
//        FlickrResponseDto response = new FlickrResponseDto(photos,"OK");
//
//        Converter convert = new Converter();
//        List<Photo> convertList = convert.convert(response);

//        return convertList;

        Call<FlickrResponseDto> flickrPhotosResponseCall = service.getPhotos(query, getResources().getString(R.string.flickr_api_key),flickrPerPage);

        flickrPhotosResponseCall.enqueue(new Callback<FlickrResponseDto>() {
            @Override
            public void onResponse(Call<FlickrResponseDto> call, Response<FlickrResponseDto> response) {
                Log.e("onResponse :", response.toString() + " -> " + call.request().url());

                Converter convert = new Converter();
                List<Photo> convertList = convert.convert(response.body());
                Log.e("onResponse :", convertList.toString());
                interfaceResponse.onPhotoReceived(convertList);

            }

            @Override
            public void onFailure(Call<FlickrResponseDto> call, Throwable t) {
                Log.e("onFailure :", t.toString() + " -> " + call.request().url());
            }

        });

    }


}


package com.guylaf.photo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by guyla on 28/11/2016.
 */

public interface FlickrService {
    @GET("services/rest/?method=flickr.photos.search&safe_search=1&per_page=5&format=json&nojsoncallback=1")
    Call<FlickrResponseDto> getPhotos(@Query("tags") String query , @Query("api_key") String apiKey);
}

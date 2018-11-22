package com.eaziche.zoo.utils;

import com.eaziche.zoo.models.Animal;
import com.eaziche.zoo.models.GalleryImage;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by hardik on 23-11-2016.
 */

public interface GalleryApiInterface {
    @GET("/gallery.json")
    void getStream(Callback<List<GalleryImage>> callback);

}

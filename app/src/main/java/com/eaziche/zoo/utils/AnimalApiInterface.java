package com.eaziche.zoo.utils;

import com.eaziche.zoo.models.Animal;
import com.eaziche.zoo.models.GalleryImage;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by hardik on 23-11-2016.
 */

public interface AnimalApiInterface {
    @GET("/exibit.json")
    void getStream(Callback<List<Animal>> callback);
}

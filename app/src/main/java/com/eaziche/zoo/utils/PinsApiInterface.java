package com.eaziche.zoo.utils;

import com.eaziche.zoo.models.Pin;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by paulruiz on 5/3/15.
 */
public interface PinsApiInterface {

    @GET( "/Pins.json" )
    void getStreams(Callback<List<Pin>> callback);
}

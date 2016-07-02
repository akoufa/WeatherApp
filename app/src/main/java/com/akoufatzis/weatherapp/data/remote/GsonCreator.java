package com.akoufatzis.weatherapp.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by alexk on 02/05/16.
 */
public class GsonCreator {

    public static Gson createGson() {

        // register possible type adapters here
        return new GsonBuilder().create();
    }
}

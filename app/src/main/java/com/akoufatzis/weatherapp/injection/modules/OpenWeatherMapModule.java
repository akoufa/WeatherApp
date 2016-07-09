package com.akoufatzis.weatherapp.injection.modules;

import com.akoufatzis.weatherapp.data.remote.IOpenWeatherMapApi;
import com.akoufatzis.weatherapp.injection.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by alexk on 05/05/16.
 */

@Module
public class OpenWeatherMapModule {

    @Provides
    @PerApplication
    IOpenWeatherMapApi providesOpenWeatherMapApi(Retrofit retrofit) {

        return retrofit.create(IOpenWeatherMapApi.class);
    }
}
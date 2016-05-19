package com.akoufatzis.weatherapp.application.injection.modules;

import com.akoufatzis.weatherapp.application.injection.scopes.PerApplication;
import com.akoufatzis.weatherapp.communication.IOpenWeatherMapApi;

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
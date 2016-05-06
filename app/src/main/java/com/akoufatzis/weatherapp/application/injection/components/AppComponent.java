package com.akoufatzis.weatherapp.application.injection.components;

import android.app.Application;

import com.akoufatzis.weatherapp.WeatherApplication;
import com.akoufatzis.weatherapp.application.injection.modules.AppModule;
import com.akoufatzis.weatherapp.application.injection.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by alexk on 05/05/16.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class
        }
)
public interface AppComponent {

    void inject(WeatherApplication weatherApplication);

    Application getApplication();

    Retrofit getRetrofit();
}

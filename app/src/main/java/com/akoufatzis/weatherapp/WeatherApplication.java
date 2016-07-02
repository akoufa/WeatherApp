package com.akoufatzis.weatherapp;

import android.app.Application;

import com.akoufatzis.weatherapp.injection.components.AppComponent;
import com.akoufatzis.weatherapp.injection.components.DaggerAppComponent;
import com.akoufatzis.weatherapp.injection.components.DaggerOpenWeatherMapComponent;
import com.akoufatzis.weatherapp.injection.components.OpenWeatherMapComponent;
import com.akoufatzis.weatherapp.injection.modules.AppModule;
import com.akoufatzis.weatherapp.injection.modules.NetworkModule;

/**
 * Created by alexk on 05/05/16.
 */
public class WeatherApplication extends Application {

    private OpenWeatherMapComponent openWeatherMapComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        AppComponent appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.OPENWEATHERMAP_URL))
                .build();

        openWeatherMapComponent = DaggerOpenWeatherMapComponent
                .builder()
                .appComponent(appComponent)
                .build();
    }

    public OpenWeatherMapComponent getOpenWeatherMapComponent() {
        return openWeatherMapComponent;
    }
}

package com.akoufatzis.weatherapp;

import android.app.Application;

import com.akoufatzis.weatherapp.application.injection.components.AppComponent;
import com.akoufatzis.weatherapp.application.injection.components.CityWeatherSearchActivityComponent;
import com.akoufatzis.weatherapp.application.injection.components.DaggerAppComponent;
import com.akoufatzis.weatherapp.application.injection.components.DaggerCityWeatherSearchActivityComponent;
import com.akoufatzis.weatherapp.application.injection.components.DaggerOpenWeatherMapComponent;
import com.akoufatzis.weatherapp.application.injection.components.OpenWeatherMapComponent;
import com.akoufatzis.weatherapp.application.injection.modules.AppModule;
import com.akoufatzis.weatherapp.application.injection.modules.CityWeatherSearchActivityModule;
import com.akoufatzis.weatherapp.application.injection.modules.NetworkModule;

/**
 * Created by alexk on 05/05/16.
 */
public class WeatherApplication extends Application {

    AppComponent appComponent;
    OpenWeatherMapComponent openWeatherMapComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("http://api.openweathermap.org/data/2.5/"))
                .build();

        openWeatherMapComponent = DaggerOpenWeatherMapComponent
                .builder()
                .appComponent(appComponent)
                .build();
    }

    public CityWeatherSearchActivityComponent getCityWeatherSearchActivityComponent() {

        return DaggerCityWeatherSearchActivityComponent
                .builder()
                .cityWeatherSearchActivityModule(new CityWeatherSearchActivityModule(openWeatherMapComponent.getDataManager()))
                .openWeatherMapComponent(openWeatherMapComponent)
                .build();
    }
}

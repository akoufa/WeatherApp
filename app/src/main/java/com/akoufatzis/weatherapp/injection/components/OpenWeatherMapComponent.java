package com.akoufatzis.weatherapp.injection.components;

import com.akoufatzis.weatherapp.injection.modules.OpenWeatherMapModule;
import com.akoufatzis.weatherapp.injection.scopes.PerApplication;
import com.akoufatzis.weatherapp.data.remote.DataManager;

import dagger.Component;

/**
 * Created by alexk on 05/05/16.
 */
@PerApplication
@Component(
        modules = OpenWeatherMapModule.class,
        dependencies = AppComponent.class
)
public interface OpenWeatherMapComponent {

    DataManager getDataManager();
}

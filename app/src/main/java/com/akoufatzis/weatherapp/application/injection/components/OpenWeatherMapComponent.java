package com.akoufatzis.weatherapp.application.injection.components;

import com.akoufatzis.weatherapp.application.injection.modules.OpenWeatherMapModule;
import com.akoufatzis.weatherapp.application.injection.scopes.PerApplication;
import com.akoufatzis.weatherapp.communication.IOpenWeatherMapApi;

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

    IOpenWeatherMapApi getOpenWeatherMapApi();
}

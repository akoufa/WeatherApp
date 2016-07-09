package com.akoufatzis.weatherapp.cityweatherdetails.injection;

import com.akoufatzis.weatherapp.cityweatherdetails.view.CityWeatherDetailsActivity;
import com.akoufatzis.weatherapp.injection.components.OpenWeatherMapComponent;
import com.akoufatzis.weatherapp.injection.scopes.PerActivity;

import dagger.Component;

/**
 * Created by alexk on 04/06/16.
 */
@PerActivity
@Component(
        dependencies = OpenWeatherMapComponent.class,
        modules = CityWeatherDetailsModule.class
)
public interface CityWeatherDetailsComponent {

    CityWeatherDetailsActivity inject(CityWeatherDetailsActivity cityWeatherDetailsActivity);
}

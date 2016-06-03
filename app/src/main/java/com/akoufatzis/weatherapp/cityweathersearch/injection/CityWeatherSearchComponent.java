package com.akoufatzis.weatherapp.cityweathersearch.injection;

import com.akoufatzis.weatherapp.application.injection.components.OpenWeatherMapComponent;
import com.akoufatzis.weatherapp.application.injection.scopes.PerActivity;
import com.akoufatzis.weatherapp.cityweathersearch.view.CityWeatherSearchActivity;

import dagger.Component;

/**
 * Created by alexk on 05/05/16.
 */
@PerActivity
@Component(
        dependencies = OpenWeatherMapComponent.class,
        modules = CityWeatherSearchModule.class
)
public interface CityWeatherSearchComponent {

    CityWeatherSearchActivity inject(CityWeatherSearchActivity activity);
}

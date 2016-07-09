package com.akoufatzis.weatherapp.cityweathersearch.injection;

import com.akoufatzis.weatherapp.cityweathersearch.view.CityWeatherSearchActivity;
import com.akoufatzis.weatherapp.injection.components.OpenWeatherMapComponent;
import com.akoufatzis.weatherapp.injection.scopes.PerActivity;

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

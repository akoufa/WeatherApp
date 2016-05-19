package com.akoufatzis.weatherapp.application.injection.components;

import com.akoufatzis.weatherapp.application.injection.modules.CityWeatherSearchActivityModule;
import com.akoufatzis.weatherapp.application.injection.scopes.PerActivity;
import com.akoufatzis.weatherapp.cityweathersearch.view.CityWeatherSearchActivity;

import dagger.Component;

/**
 * Created by alexk on 05/05/16.
 */
@PerActivity
@Component(
        dependencies = OpenWeatherMapComponent.class,
        modules = CityWeatherSearchActivityModule.class
)
/**
 * Generic Activity Component to declare all the Activities that support injection
 */
public interface ActivityComponent {

    CityWeatherSearchActivity inject(CityWeatherSearchActivity activity);
}

package com.akoufatzis.weatherapp.cityweatherfavorite.injection;

import com.akoufatzis.weatherapp.cityweatherfavorite.view.CityWeatherFavoriteFragment;
import com.akoufatzis.weatherapp.injection.components.OpenWeatherMapComponent;
import com.akoufatzis.weatherapp.injection.scopes.PerActivity;

import dagger.Component;

/**
 * Created by alexk on 12/07/16.
 */
@PerActivity
@Component(
        dependencies = OpenWeatherMapComponent.class,
        modules = CityWeatherFavoriteModule.class)
public interface CityWeatherFavoriteComponent {

    CityWeatherFavoriteFragment inject(CityWeatherFavoriteFragment fragment);
}
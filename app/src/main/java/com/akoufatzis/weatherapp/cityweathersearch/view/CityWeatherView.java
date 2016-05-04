package com.akoufatzis.weatherapp.cityweathersearch.view;

import com.akoufatzis.weatherapp.base.MvpModelListView;
import com.akoufatzis.weatherapp.model.CityWeather;

/**
 * Created by alexk on 01/05/16.
 */
public interface CityWeatherView extends MvpModelListView<CityWeather> {

    void addData(CityWeather cityWeather);
}

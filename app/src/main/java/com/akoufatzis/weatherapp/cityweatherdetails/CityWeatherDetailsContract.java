package com.akoufatzis.weatherapp.cityweatherdetails;

import com.akoufatzis.weatherapp.base.MvpView;
import com.akoufatzis.weatherapp.model.CityWeather;

/**
 * Created by alexk on 03/06/16.
 */
public interface CityWeatherDetailsContract {

    interface View extends MvpView {

        void showCityWeatherData(CityWeather cityWeather);
    }

    interface Presenter extends com.akoufatzis.weatherapp.base.Presenter<View> {

        void loadCityWeatherData(long id);
    }
}
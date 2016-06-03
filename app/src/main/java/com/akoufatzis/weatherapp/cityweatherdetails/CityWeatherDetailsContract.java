package com.akoufatzis.weatherapp.cityweatherdetails;

import com.akoufatzis.weatherapp.base.MvpView;

/**
 * Created by alexk on 03/06/16.
 */
public interface CityWeatherDetailsContract {

    interface View extends MvpView {

    }

    interface Presenter extends com.akoufatzis.weatherapp.base.Presenter<View> {

        void loadCityWeatherData(long id);
    }
}
package com.akoufatzis.weatherapp.cityweatherdetails.presenter;

import com.akoufatzis.weatherapp.base.MvpBasePresenter;
import com.akoufatzis.weatherapp.cityweatherdetails.CityWeatherDetailsContract;
import com.akoufatzis.weatherapp.communication.DataManager;

/**
 * Created by alexk on 03/06/16.
 */
public class CityWeatherDetailsPresenter extends MvpBasePresenter<CityWeatherDetailsContract.View>
        implements CityWeatherDetailsContract.Presenter {

    private DataManager dataManager;

    public CityWeatherDetailsPresenter(DataManager dataManager) {

        this.dataManager = dataManager;
    }

    @Override
    public void loadCityWeatherData(long id) {

        dataManager
                .getWeatherByCityId(id)
                .subscribe(cityWeather -> {

                    if (getView() != null) {

                        getView().showCityWeatherData(cityWeather);
                    }
                });
    }
}

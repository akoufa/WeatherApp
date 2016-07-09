package com.akoufatzis.weatherapp.cityweatherdetails.injection;

import com.akoufatzis.weatherapp.cityweatherdetails.CityWeatherDetailsContract;
import com.akoufatzis.weatherapp.cityweatherdetails.presenter.CityWeatherDetailsPresenter;
import com.akoufatzis.weatherapp.data.remote.DataManager;
import com.akoufatzis.weatherapp.injection.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexk on 03/06/16.
 */
@Module
public class CityWeatherDetailsModule {

    @PerActivity
    @Provides
    CityWeatherDetailsContract.Presenter providesPresenter(DataManager dataManager) {

        return new CityWeatherDetailsPresenter(dataManager);
    }
}

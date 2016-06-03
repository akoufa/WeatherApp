package com.akoufatzis.weatherapp.cityweathersearch.injection;

import com.akoufatzis.weatherapp.application.injection.scopes.PerActivity;
import com.akoufatzis.weatherapp.cityweathersearch.CityWeatherSearchContract;
import com.akoufatzis.weatherapp.cityweathersearch.presenter.CityWeatherSearchPresenter;
import com.akoufatzis.weatherapp.communication.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexk on 05/05/16.
 */
@Module
public class CityWeatherSearchModule {

    @PerActivity
    @Provides
    CityWeatherSearchContract.Presenter providesPresenter(DataManager dataManager) {

        return new CityWeatherSearchPresenter(dataManager);
    }
}
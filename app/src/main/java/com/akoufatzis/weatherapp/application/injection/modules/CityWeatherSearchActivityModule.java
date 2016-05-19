package com.akoufatzis.weatherapp.application.injection.modules;

import com.akoufatzis.weatherapp.application.injection.scopes.PerActivity;
import com.akoufatzis.weatherapp.base.MvpBaseSearchPresenter;
import com.akoufatzis.weatherapp.cityweathersearch.presenter.CityWeatherSearchPresenter;
import com.akoufatzis.weatherapp.cityweathersearch.view.CityWeatherView;
import com.akoufatzis.weatherapp.communication.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexk on 05/05/16.
 */
@Module
public class CityWeatherSearchActivityModule {

    @PerActivity
    @Provides
    MvpBaseSearchPresenter<CityWeatherView> providesPresenter(DataManager dataManager) {

        return new CityWeatherSearchPresenter(dataManager);
    }
}
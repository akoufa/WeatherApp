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

    DataManager dataManager;

    public CityWeatherSearchActivityModule(DataManager dataManager) {

        this.dataManager = dataManager;
    }

    @PerActivity
    @Provides
    MvpBaseSearchPresenter<CityWeatherView> providesPresenter() {

        return new CityWeatherSearchPresenter(dataManager);
    }
}

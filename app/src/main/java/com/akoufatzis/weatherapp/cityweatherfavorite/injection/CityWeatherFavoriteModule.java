package com.akoufatzis.weatherapp.cityweatherfavorite.injection;

import com.akoufatzis.weatherapp.cityweatherfavorite.CityWeatherFavoriteContract;
import com.akoufatzis.weatherapp.cityweatherfavorite.presenter.CityWeatherFavoritePresenter;
import com.akoufatzis.weatherapp.data.remote.DataManager;
import com.akoufatzis.weatherapp.injection.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexk on 12/07/16.
 */
@Module
public class CityWeatherFavoriteModule {

    @PerActivity
    @Provides
    CityWeatherFavoriteContract.Presenter providesPresenter(DataManager dataManager) {

        return new CityWeatherFavoritePresenter(dataManager);
    }
}

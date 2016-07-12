package com.akoufatzis.weatherapp.cityweatherfavorite.presenter;

import android.util.Log;

import com.akoufatzis.weatherapp.cityweatherfavorite.CityWeatherFavoriteContract;
import com.akoufatzis.weatherapp.common.MvpBasePresenter;
import com.akoufatzis.weatherapp.data.remote.DataManager;
import com.akoufatzis.weatherapp.model.CityWeather;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alexk on 12/07/16.
 */
public class CityWeatherFavoritePresenter extends MvpBasePresenter<CityWeatherFavoriteContract.View>
        implements CityWeatherFavoriteContract.Presenter {

    private final DataManager dataManager;

    @Inject
    public CityWeatherFavoritePresenter(DataManager dataManager) {

        this.dataManager = dataManager;
    }

    @Override
    public void getFavoriteData() {

        dataManager
                .getAllFavoriteCityWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityWeathers -> {
                    if (getView() != null) {

                        getView().showData(cityWeathers);
                    }
                }, throwable -> {

                    Log.e("Error", throwable.toString());
                });
    }

    @Override
    public void onFavoriteSelected(CityWeather cityWeather) {

        // TODO: Implement favorite handling
    }
}

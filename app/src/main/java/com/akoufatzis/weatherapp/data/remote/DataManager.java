package com.akoufatzis.weatherapp.data.remote;

import com.akoufatzis.weatherapp.BuildConfig;
import com.akoufatzis.weatherapp.data.local.DatabaseHelper;
import com.akoufatzis.weatherapp.data.memory.MemoryCache;
import com.akoufatzis.weatherapp.injection.scopes.PerApplication;
import com.akoufatzis.weatherapp.model.CityWeather;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alexk on 02/05/16.
 */
@PerApplication
public class DataManager {

    private MemoryCache memoryCache;
    private IOpenWeatherMapApi openWeatherMapService;
    private DatabaseHelper databaseHelper;
    private final String apiKey = BuildConfig.OPENWEATHERMAP_API_KEY;

    @Inject
    public DataManager(IOpenWeatherMapApi openWeatherMapService, DatabaseHelper databaseHelper) {

        this.openWeatherMapService = openWeatherMapService;
        this.databaseHelper = databaseHelper;
        memoryCache = new MemoryCache();
    }

    //TODO: Implement caching
    public Observable<CityWeather> getWeatherByCityName(String name) {

        return openWeatherMapService
                .getWeatherByCityName(name, apiKey)
                .compose(applySchedulers());
    }

    public Observable<CityWeather> getWeatherByCityId(long id) {

        return openWeatherMapService
                .getWeatherByCityId(id, apiKey)
                .compose(applySchedulers());
    }

    public Observable<Void> addCityWeatherToFavorites(CityWeather cityWeather) {

        return databaseHelper
                .addCityWeatherToDb(cityWeather)
                .compose(applySchedulers());
    }

    public Observable<Boolean> isCityWeatherFavorite(long id) {
        return databaseHelper
                .findFavoriteCityWeatherById(id)
                .map(cityWeather -> {

                    if (cityWeather != null) {

                        return cityWeather.isFavorite();
                    } else {

                        return false;
                    }
                })
                .compose(applySchedulers());
    }

    public Observable<List<CityWeather>> getAllFavoriteCityWeather() {

        return databaseHelper
                .getAllFavoriteCityWeather()
                .compose(applySchedulers());
    }

    public Observable<Void> removeCityWeatherFromFavorites(CityWeather cityWeather) {

        return databaseHelper
                .deleteFavoriteCityWeatherById(cityWeather.getId())
                .compose(applySchedulers());
    }

    //Schedulers Transformer

    private <T> Observable.Transformer<T, T> applySchedulers() {

        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

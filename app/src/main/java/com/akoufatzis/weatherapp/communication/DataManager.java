package com.akoufatzis.weatherapp.communication;

import com.akoufatzis.weatherapp.application.injection.scopes.PerApplication;
import com.akoufatzis.weatherapp.communication.memory.MemoryCache;
import com.akoufatzis.weatherapp.model.CityWeather;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alexk on 02/05/16.
 */
@PerApplication
public class DataManager {

    // TODO: Introduce enviroment variable
    private static final String APP_ID = "your api key here";

    private MemoryCache memoryCache;
    private IOpenWeatherMapApi openWeatherMapService;

    @Inject
    public DataManager(IOpenWeatherMapApi openWeatherMapService) {

        this.openWeatherMapService = openWeatherMapService;
        memoryCache = new MemoryCache();
    }

    //TODO: Implement caching
    public Observable<CityWeather> getWeatherByCityName(String name) {

        return openWeatherMapService
                .getWeatherByCityName(name, APP_ID)
                .compose(applySchedulers());
    }

    //Schedulers Transformer

    private <T> Observable.Transformer<T, T> applySchedulers() {

        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

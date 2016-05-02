package com.akoufatzis.weatherapp.communication;

import com.akoufatzis.weatherapp.communication.memory.MemoryCache;
import com.akoufatzis.weatherapp.model.CityWeather;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alexk on 02/05/16.
 */
public class DataManager {

    // TODO: Introduce enviroment variable
    private static final String APP_ID = "your api key here";

    private static DataManager instance;
    private MemoryCache memoryCache;
    private IOpenWeatherMapService openWeatherMapService;

    private DataManager(IOpenWeatherMapService openWeatherMapService) {

        this.openWeatherMapService = openWeatherMapService;
    }

    public static DataManager newInstance() {

        if (instance == null) {

            instance = new DataManager(ServiceCreator.create(IOpenWeatherMapService.class, "http://api.openweathermap.org/data/2.5/"));
            instance.memoryCache = new MemoryCache();
        }

        return instance;
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

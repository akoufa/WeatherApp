package com.akoufatzis.weatherapp.data.remote;

import com.akoufatzis.weatherapp.model.CityWeather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by alexk on 02/05/16.
 */
public interface IOpenWeatherMapApi {

    @GET("weather")
    public Observable<CityWeather> getWeatherByCityName(@Query("q") String cityName, @Query("appid") String appId);

    @GET("weather")
    public Observable<CityWeather> getWeatherByCityId(@Query("id") long cityId, @Query("appid") String appId);
}

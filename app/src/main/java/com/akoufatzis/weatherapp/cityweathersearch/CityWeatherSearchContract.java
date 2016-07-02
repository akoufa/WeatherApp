package com.akoufatzis.weatherapp.cityweathersearch;

import com.akoufatzis.weatherapp.common.MvpModelListView;
import com.akoufatzis.weatherapp.model.CityWeather;

import rx.Observable;

/**
 * Created by alexk on 03/06/16.
 */
public interface CityWeatherSearchContract {

    interface View extends MvpModelListView<CityWeather> {
    }

    interface Presenter extends com.akoufatzis.weatherapp.common.Presenter<View> {

        void onSearchTextChanged(Observable<CharSequence> searchObservable);

        void onCityWeatherSelected(CityWeather cityWeather);

        void onFavoriteSelected(CityWeather cityWeather);
    }
}

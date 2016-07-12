package com.akoufatzis.weatherapp.cityweatherfavorite;

import com.akoufatzis.weatherapp.common.MvpModelListView;
import com.akoufatzis.weatherapp.model.CityWeather;

/**
 * Created by alexk on 10/07/16.
 */
public interface CityWeatherFavoriteContract {

    interface View extends MvpModelListView<CityWeather> {

    }

    interface Presenter extends com.akoufatzis.weatherapp.common.Presenter<View> {

        void onFavoriteSelected(CityWeather cityWeather);

        void getFavoriteData();
    }
}

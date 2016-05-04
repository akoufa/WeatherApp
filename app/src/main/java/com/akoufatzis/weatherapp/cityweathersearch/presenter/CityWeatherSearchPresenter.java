package com.akoufatzis.weatherapp.cityweathersearch.presenter;

import android.util.Log;

import com.akoufatzis.weatherapp.base.MvpBaseSearchPresenter;
import com.akoufatzis.weatherapp.cityweathersearch.view.CityWeatherView;
import com.akoufatzis.weatherapp.communication.DataManager;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by alexk on 01/05/16.
 */
public class CityWeatherSearchPresenter extends MvpBaseSearchPresenter<CityWeatherView> {

    DataManager dataManager;

    public CityWeatherSearchPresenter() {

        // TODO: Implement with dagger 2
        dataManager = DataManager.newInstance();
    }

    @Override
    public void onSearchTextChanged(Observable<CharSequence> searchObservable) {

        searchObservable
                .debounce(300, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .map(String::trim)
                .filter(searchTerm -> searchTerm.length() > 2)
                .distinctUntilChanged()
                // use switchmap to cancel the previous request
                .switchMap(searchTerm -> dataManager.getWeatherByCityName(searchTerm))
                .subscribe(cityWeather -> {

                    if (getView() != null) {

                        getView().addData(cityWeather);
                    }
                }, error -> {

                    Log.d("onError", error.toString());
                });
    }
}

package com.akoufatzis.weatherapp.cityweathersearch.presenter;

import android.util.Log;

import com.akoufatzis.weatherapp.base.MvpBaseSearchPresenter;
import com.akoufatzis.weatherapp.cityweathersearch.view.CitiesWeatherView;
import com.akoufatzis.weatherapp.communication.DataManager;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by alexk on 01/05/16.
 */
public class CitiesWeatherSearchPresenter extends MvpBaseSearchPresenter<CitiesWeatherView> {

    DataManager dataManager;

    public CitiesWeatherSearchPresenter() {

        // TODO: Implement with dagger 2
        dataManager = DataManager.newInstance();
    }

    @Override
    public void onSearchTextChanged(Observable<CharSequence> searchObservable) {

        searchObservable
                .debounce(200, TimeUnit.MILLISECONDS)
                .filter(searchTerm -> searchTerm.length() > 2)
                // use switchmap if we want to cancel the previous request
                .flatMap(searchTerm -> dataManager.getWeatherByCityName(searchTerm.toString()))
                .subscribe(cityWeather -> {

                    if (getView() != null) {

                        getView().addData(cityWeather);
                    }
                }, error -> {

                    Log.d("onError", error.toString());
                });
    }
}

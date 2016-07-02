package com.akoufatzis.weatherapp.cityweathersearch.presenter;

import android.util.Log;

import com.akoufatzis.weatherapp.cityweathersearch.CityWeatherSearchContract;
import com.akoufatzis.weatherapp.common.MvpBasePresenter;
import com.akoufatzis.weatherapp.data.remote.DataManager;
import com.akoufatzis.weatherapp.injection.scopes.PerActivity;
import com.akoufatzis.weatherapp.model.CityWeather;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by alexk on 01/05/16.
 */
@PerActivity
public class CityWeatherSearchPresenter extends MvpBasePresenter<CityWeatherSearchContract.View>
        implements CityWeatherSearchContract.Presenter {

    private DataManager dataManager;

    @Inject
    public CityWeatherSearchPresenter(DataManager dataManager) {

        this.dataManager = dataManager;
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
                .switchMap(dataManager::getWeatherByCityName)
                .flatMap(cityWeather ->
                        dataManager.isCityWeatherFavorite(cityWeather.getId())
                                .flatMap(favorite -> {
                                    cityWeather.setFavorite(favorite);
                                    return Observable.<CityWeather>just(cityWeather);
                                }))
                .subscribe(cityWeather -> {

                    if (getView() != null) {

                        getView().addData(cityWeather);
                    }
                }, error -> {

                    Log.d("onError", error.toString());
                });
    }

    @Override
    public void onFavoriteSelected(CityWeather cityWeather) {

        if (cityWeather.isFavorite()) {

            dataManager.addCityWeatherToFavorites(cityWeather)
                    .subscribe(aVoid -> {
                    }, Throwable::printStackTrace);
        } else {

            dataManager.removeCityWeatherFromFavorites(cityWeather)
                    .subscribe(aVoid -> {
                    }, Throwable::printStackTrace);
        }
    }

    @Override
    public void onCityWeatherSelected(CityWeather cityWeather) {

    }
}

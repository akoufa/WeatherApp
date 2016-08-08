package com.akoufatzis.weatherapp;

import com.akoufatzis.weatherapp.cityweathersearch.CityWeatherSearchContract;
import com.akoufatzis.weatherapp.cityweathersearch.presenter.CityWeatherSearchPresenter;
import com.akoufatzis.weatherapp.data.remote.DataManager;
import com.akoufatzis.weatherapp.model.CityWeather;
import com.akoufatzis.weatherapp.rules.RxJavaTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import rx.Observable;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by alexk on 26/06/16.
 */
public class CityWeatherSearchPresenterTest {

    public static final String CITY_NAME = "Karlsruhe";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public RxJavaTestRule rxJavaTestRule = new RxJavaTestRule();

    @Mock
    public DataManager dataManager;
    @Mock
    public CityWeatherSearchContract.View view;
    @Mock
    public CityWeather cityWeather;

    @InjectMocks
    public CityWeatherSearchPresenter presenter;

    @Before
    public void setUp() {

        presenter.attachView(view);
    }

    @After
    public void tearDown() {

        presenter.detachView(false);
    }

    @Test
    public void searchForCityWeather() {

        // given
        String cityName = CITY_NAME;

        // when
        when(dataManager.getWeatherByCityName(cityName)).thenReturn(Observable.just(cityWeather));
        when(dataManager.isCityWeatherFavorite(anyLong())).thenReturn(Observable.just(true));
        presenter.onSearchTextChanged(Observable.just(cityName));

        // then
        verify(dataManager).getWeatherByCityName(anyString());
        verify(view).addData(cityWeather);
    }

    @Test
    public void onFavorityCityWeatherSelectedShouldRemoveIt() {

        // given
        when(cityWeather.isFavorite()).thenReturn(true);
        when(dataManager.removeCityWeatherFromFavorites(anyObject())).thenReturn(Observable.just(null));

        // when
        presenter.onFavoriteSelected(cityWeather);

        // then
        verify(dataManager).removeCityWeatherFromFavorites(anyObject());
    }

    @Test
    public void onNonFavoriteCityWeatherSelectedShouldAddIt() {

        // given
        when(cityWeather.isFavorite()).thenReturn(false);
        when(dataManager.addCityWeatherToFavorites(anyObject())).thenReturn(Observable.just(null));

        // when
        presenter.onFavoriteSelected(cityWeather);

        // then
        verify(dataManager).addCityWeatherToFavorites(anyObject());
    }
}

package com.akoufatzis.weatherapp;

import com.akoufatzis.weatherapp.cityweathersearch.CityWeatherSearchContract;
import com.akoufatzis.weatherapp.cityweathersearch.presenter.CityWeatherSearchPresenter;
import com.akoufatzis.weatherapp.communication.DataManager;
import com.akoufatzis.weatherapp.model.CityWeather;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import rx.Observable;

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

    @Test
    public void searchForCityWeather() {

        when(dataManager.getWeatherByCityName(CITY_NAME)).thenReturn(Observable.just(cityWeather));
        presenter.onSearchTextChanged(Observable.just(CITY_NAME));

        verify(dataManager).getWeatherByCityName(anyString());
        verify(view).addData(cityWeather);
    }
}

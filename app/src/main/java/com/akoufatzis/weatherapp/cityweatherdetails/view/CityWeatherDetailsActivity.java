package com.akoufatzis.weatherapp.cityweatherdetails.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.akoufatzis.weatherapp.R;
import com.akoufatzis.weatherapp.WeatherApplication;
import com.akoufatzis.weatherapp.base.BaseToolbarActivity;
import com.akoufatzis.weatherapp.cityweatherdetails.CityWeatherDetailsContract;
import com.akoufatzis.weatherapp.cityweatherdetails.injection.DaggerCityWeatherDetailsComponent;
import com.akoufatzis.weatherapp.model.CityWeather;
import com.akoufatzis.weatherapp.utilities.WeatherUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by alexk on 03/06/16.
 */
public class CityWeatherDetailsActivity extends BaseToolbarActivity
        implements CityWeatherDetailsContract.View {

    public static String CITY_ID_EXTRA = "city_id_extra";
    public static String CITY_NAME_EXTRA = "city_name_extra";

    @BindView(R.id.city_weather_icon_imageview)
    ImageView cityWeatherIconImageView;

    @Inject
    CityWeatherDetailsContract.Presenter presenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_cityweatherdetails;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        long id = intent.getLongExtra(CITY_ID_EXTRA, Long.MIN_VALUE);
        String cityName = intent.getStringExtra(CITY_NAME_EXTRA);

        if (id == Long.MIN_VALUE || TextUtils.isEmpty(cityName)) {

            throw new IllegalArgumentException("id and cityName must be defined");
        }

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(cityName);
        }

        DaggerCityWeatherDetailsComponent
                .builder()
                .openWeatherMapComponent(((WeatherApplication) getApplication()).getOpenWeatherMapComponent())
                .build()
                .inject(this);

        presenter.attachView(this);
        presenter.loadCityWeatherData(id);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView(false);
        super.onDestroy();
    }

    @Override
    public void showCityWeatherData(CityWeather cityWeather) {

        cityWeatherIconImageView
                .setImageResource(WeatherUtils
                        .getArtResourceForWeatherCondition(cityWeather.getWeather().get(0).getId()));
    }
}

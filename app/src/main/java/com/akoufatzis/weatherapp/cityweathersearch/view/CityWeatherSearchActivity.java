package com.akoufatzis.weatherapp.cityweathersearch.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.akoufatzis.weatherapp.R;
import com.akoufatzis.weatherapp.WeatherApplication;
import com.akoufatzis.weatherapp.base.BaseToolbarActivity;
import com.akoufatzis.weatherapp.base.MvpBaseSearchPresenter;
import com.akoufatzis.weatherapp.cityweathersearch.CityWeatherAdapter;
import com.akoufatzis.weatherapp.model.CityWeather;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexk on 02/05/16.
 */
public class CityWeatherSearchActivity extends BaseToolbarActivity implements CityWeatherView {

    @BindView(R.id.citiesweathersearch_edittext)
    EditText searchEditText;

    @BindView(R.id.citiesweathersearch_recyclerview)
    RecyclerView cityWeatherRecyclerView;

    @Inject
    MvpBaseSearchPresenter<CityWeatherView> presenter;

    private CityWeatherAdapter cityWeatherAdapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_citiesweathersearch;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        ((WeatherApplication) getApplication()).getCityWeatherSearchActivityComponent().inject(this);

        presenter.attachView(this);

        // Informing the presenter
        presenter.onSearchTextChanged(RxTextView.textChanges(searchEditText));

        cityWeatherAdapter = new CityWeatherAdapter(this, new ArrayList<>());
        cityWeatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cityWeatherRecyclerView.setAdapter(cityWeatherAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
    }

    @Override
    public void showData(List<CityWeather> data) {

    }

    @Override
    public void addData(CityWeather cityWeather) {

        cityWeatherAdapter.addCityWeather(cityWeather);
        cityWeatherRecyclerView.scrollToPosition(0);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError(Throwable e) {

    }
}

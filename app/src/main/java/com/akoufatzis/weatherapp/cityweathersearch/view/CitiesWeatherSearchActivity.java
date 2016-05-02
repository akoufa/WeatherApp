package com.akoufatzis.weatherapp.cityweathersearch.view;

import android.os.Bundle;
import android.widget.EditText;

import com.akoufatzis.weatherapp.R;
import com.akoufatzis.weatherapp.base.BaseToolbarActivity;
import com.akoufatzis.weatherapp.base.MvpBaseSearchPresenter;
import com.akoufatzis.weatherapp.cityweathersearch.presenter.CitiesWeatherSearchPresenter;
import com.akoufatzis.weatherapp.model.CityWeather;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexk on 02/05/16.
 */
public class CitiesWeatherSearchActivity extends BaseToolbarActivity implements CitiesWeatherView {

    @BindView(R.id.citiesweathersearch_edittext)
    EditText searchEditText;

    private MvpBaseSearchPresenter<CitiesWeatherView> presenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_citiesweathersearch;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        presenter = new CitiesWeatherSearchPresenter();
        presenter.attachView(this);

        // Informing the presenter
        presenter.onSearchTextChanged(RxTextView.textChanges(searchEditText));

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

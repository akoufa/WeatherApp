package com.akoufatzis.weatherapp.cityweathersearch.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.akoufatzis.weatherapp.R;
import com.akoufatzis.weatherapp.WeatherApplication;
import com.akoufatzis.weatherapp.cityweatherdetails.view.CityWeatherDetailsActivity;
import com.akoufatzis.weatherapp.cityweathersearch.CityWeatherAdapter;
import com.akoufatzis.weatherapp.cityweathersearch.CityWeatherAdapter.OnCityWeatherClickListener;
import com.akoufatzis.weatherapp.cityweathersearch.CityWeatherAdapter.OnCityWeatherFavoriteSelectListener;
import com.akoufatzis.weatherapp.cityweathersearch.CityWeatherSearchContract;
import com.akoufatzis.weatherapp.cityweathersearch.injection.DaggerCityWeatherSearchComponent;
import com.akoufatzis.weatherapp.common.BaseToolbarActivity;
import com.akoufatzis.weatherapp.model.CityWeather;
import com.akoufatzis.weatherapp.widgets.ItemOffsetDecoration;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by alexk on 02/05/16.
 */
public class CityWeatherSearchActivity extends BaseToolbarActivity implements CityWeatherSearchContract.View {

    @BindView(R.id.citiesweathersearch_edittext)
    EditText searchEditText;

    @BindView(R.id.citiesweathersearch_recyclerview)
    RecyclerView cityWeatherRecyclerView;

    @Inject
    CityWeatherSearchContract.Presenter presenter;

    private CityWeatherAdapter cityWeatherAdapter;
    private boolean isLinearLayoutEnabled;
    private OnCityWeatherClickListener onCityWeatherClickListener;
    private OnCityWeatherFavoriteSelectListener onCityWeatherFavoriteSelectListener;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_citiesweathersearch;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerCityWeatherSearchComponent
                .builder()
                .openWeatherMapComponent(((WeatherApplication) getApplication()).getOpenWeatherMapComponent())
                .build()
                .inject(this);

        cityWeatherAdapter = new CityWeatherAdapter(this, new ArrayList<>(), R.layout.item_city_weather_card);
        cityWeatherRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        cityWeatherRecyclerView.addItemDecoration(itemOffsetDecoration);
        cityWeatherRecyclerView.setAdapter(cityWeatherAdapter);

        onCityWeatherClickListener = cityWeather -> {

            Intent intent = new Intent(this, CityWeatherDetailsActivity.class);
            intent.putExtra(CityWeatherDetailsActivity.CITY_ID_EXTRA, cityWeather.getId());
            intent.putExtra(CityWeatherDetailsActivity.CITY_NAME_EXTRA, cityWeather.getName());
            startActivity(intent);
        };

        onCityWeatherFavoriteSelectListener = cityWeather -> {

            presenter.onFavoriteSelected(cityWeather);
        };

        cityWeatherAdapter.setOnCityWeatherClickListener(onCityWeatherClickListener);
        cityWeatherAdapter.setOnCityWeatherFavoriteSelectListener(onCityWeatherFavoriteSelectListener);

        presenter.attachView(this);

        // Informing the presenter
        presenter.onSearchTextChanged(RxTextView.textChanges(searchEditText));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cityweathersearchmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.toggle_list:

                isLinearLayoutEnabled = !isLinearLayoutEnabled;
                toggleMenuItem(item, isLinearLayoutEnabled);
                toggleListGridLayout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toggleMenuItem(MenuItem item, boolean isLinearLayoutEnabled) {

        if (isLinearLayoutEnabled) {

            item.setIcon(R.drawable.ic_view_module_white_24dp);

        } else {

            item.setIcon(R.drawable.ic_toc_white_24dp);
        }
    }

    /**
     * Toggles between a list and a grid recyclerview
     */
    private void toggleListGridLayout() {

        cityWeatherAdapter.setOnCityWeatherClickListener(null);
        List<CityWeather> cityWeatherList = cityWeatherAdapter.getCityWeatherList();

        if (isLinearLayoutEnabled) {

            cityWeatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            cityWeatherAdapter = new CityWeatherAdapter(this, new ArrayList<>(), R.layout.item_city_weather);

        } else {
            cityWeatherRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            cityWeatherAdapter = new CityWeatherAdapter(this, new ArrayList<>(), R.layout.item_city_weather_card);
        }

        cityWeatherAdapter.setOnCityWeatherClickListener(onCityWeatherClickListener);
        cityWeatherAdapter.setOnCityWeatherFavoriteSelectListener(onCityWeatherFavoriteSelectListener);
        cityWeatherAdapter.setCityWeatherList(cityWeatherList);
        cityWeatherRecyclerView.setAdapter(cityWeatherAdapter);
    }

    @Override
    protected void onDestroy() {
        cityWeatherAdapter.clearListeners();
        presenter.detachView(false);
        super.onDestroy();
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

package com.akoufatzis.weatherapp.cityweathersearch.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.akoufatzis.weatherapp.R;
import com.akoufatzis.weatherapp.WeatherApplication;
import com.akoufatzis.weatherapp.cityweathercommon.CityWeatherAdapter;
import com.akoufatzis.weatherapp.cityweatherdetails.view.CityWeatherDetailsActivity;
import com.akoufatzis.weatherapp.cityweathersearch.CityWeatherSearchContract;
import com.akoufatzis.weatherapp.cityweathersearch.injection.DaggerCityWeatherSearchComponent;
import com.akoufatzis.weatherapp.model.CityWeather;
import com.akoufatzis.weatherapp.widgets.ItemOffsetDecoration;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexk on 12/07/16.
 */
public class CityWeatherSearchFragment extends Fragment implements CityWeatherSearchContract.View {

    @BindView(R.id.citiesweathersearch_edittext)
    EditText searchEditText;

    @BindView(R.id.citiesweathersearch_recyclerview)
    RecyclerView cityWeatherRecyclerView;

    @Inject
    CityWeatherSearchContract.Presenter presenter;

    private CityWeatherAdapter cityWeatherAdapter;
    private boolean isLinearLayoutEnabled;
    private CityWeatherAdapter.OnCityWeatherClickListener onCityWeatherClickListener;
    private CityWeatherAdapter.OnCityWeatherFavoriteSelectListener onCityWeatherFavoriteSelectListener;

    public static CityWeatherSearchFragment newInstance() {

        return new CityWeatherSearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerCityWeatherSearchComponent
                .builder()
                .openWeatherMapComponent(((WeatherApplication) getActivity().getApplication()).getOpenWeatherMapComponent())
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cityweathersearch, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        cityWeatherAdapter = new CityWeatherAdapter(getContext(), new ArrayList<>(), R.layout.item_city_weather_card);
        cityWeatherRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getContext(), R.dimen.item_offset);
        cityWeatherRecyclerView.addItemDecoration(itemOffsetDecoration);
        cityWeatherRecyclerView.setAdapter(cityWeatherAdapter);

        onCityWeatherClickListener = cityWeather -> {

            Intent intent = new Intent(getActivity(), CityWeatherDetailsActivity.class);
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

        searchEditText.requestFocus();
        // Informing the presenter
        presenter.onSearchTextChanged(RxTextView.textChanges(searchEditText));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.cityweathersearchmenu, menu);
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

            cityWeatherRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            cityWeatherAdapter = new CityWeatherAdapter(getContext(), new ArrayList<>(), R.layout.item_city_weather);

        } else {
            cityWeatherRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            cityWeatherAdapter = new CityWeatherAdapter(getContext(), new ArrayList<>(), R.layout.item_city_weather_card);
        }

        cityWeatherAdapter.setOnCityWeatherClickListener(onCityWeatherClickListener);
        cityWeatherAdapter.setOnCityWeatherFavoriteSelectListener(onCityWeatherFavoriteSelectListener);
        cityWeatherAdapter.setCityWeatherList(cityWeatherList);
        cityWeatherRecyclerView.setAdapter(cityWeatherAdapter);
    }

    @Override
    public void onDestroy() {
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

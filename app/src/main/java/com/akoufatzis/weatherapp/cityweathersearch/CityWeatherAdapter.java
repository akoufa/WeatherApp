package com.akoufatzis.weatherapp.cityweathersearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akoufatzis.weatherapp.R;
import com.akoufatzis.weatherapp.model.CityWeather;
import com.akoufatzis.weatherapp.utilities.WeatherUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexk on 03/05/16.
 */
public class CityWeatherAdapter extends RecyclerView.Adapter<CityWeatherAdapter.CityWeatherViewHolder> {

    private List<CityWeather> cityWeatherList;
    private OnCityWeatherClickListener listener;
    private Context context;

    public interface OnCityWeatherClickListener {

        void onCityWeatherClicked(CityWeather cityWeather);
    }

    public CityWeatherAdapter(Context context, List<CityWeather> cityWeatherList) {

        this.context = context;
        this.cityWeatherList = cityWeatherList;
    }

    @Override
    public CityWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_city_weather, parent, false);
        return new CityWeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CityWeatherViewHolder holder, int position) {

        CityWeather cityWeather = cityWeatherList.get(position);

        holder.cityNameTextView.setText(cityWeather.getName());

        String weatherInCelsius = " - ";

        if (cityWeather.getMain() != null) {

            weatherInCelsius = WeatherUtils.getDegreesRepresentation(context, WeatherUtils.convertToCelsius(cityWeather.getMain().getTemp()));
        }

        holder.cityWeatherDegressTextView.setText(weatherInCelsius);
        holder.cityWeatherDescriptionTextView.setText(cityWeather.getWeather().get(0).getMain());
        holder.cityWeatherIconImageView.setImageResource(WeatherUtils.getArtResourceForWeatherCondition(cityWeather.getWeather().get(0).getId()));
    }

    @Override
    public int getItemCount() {
        return cityWeatherList.size();
    }

    public void addCityWeather(CityWeather cityWeather) {

        // prepend new cityweather
        cityWeatherList.add(0, cityWeather);
        notifyItemInserted(0);
    }

    public void setOnCityWeatherClickListener(OnCityWeatherClickListener listener) {

        this.listener = listener;
    }

    public class CityWeatherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name_textview)
        TextView cityNameTextView;
        @BindView(R.id.city_weather_degrees_textview)
        TextView cityWeatherDegressTextView;
        @BindView(R.id.city_weather_description_textview)
        TextView cityWeatherDescriptionTextView;
        @BindView(R.id.city_weather_icon_imageview)
        ImageView cityWeatherIconImageView;

        @OnClick(R.id.city_weather_item_layout)
        public void onCityWeatherItemClicked() {

            if (listener != null && cityWeatherList.size() > getAdapterPosition()) {

                listener.onCityWeatherClicked(cityWeatherList.get(getAdapterPosition()));
            }
        }

        CityWeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

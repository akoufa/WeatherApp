package com.akoufatzis.weatherapp.cityweathercommon;

import android.content.Context;
import android.support.annotation.LayoutRes;
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
public class CityWeatherAdapter
        extends RecyclerView.Adapter<CityWeatherAdapter.CityWeatherViewHolder> {

    private List<CityWeather> cityWeatherList;
    private OnCityWeatherClickListener clickListener;
    private OnCityWeatherFavoriteSelectListener favoriteListener;
    private Context context;
    @LayoutRes
    private int layoutId;

    public interface OnCityWeatherClickListener {

        void onCityWeatherClicked(CityWeather cityWeather);
    }

    public interface OnCityWeatherFavoriteSelectListener {

        void onCityWeatherFavoriteSelected(CityWeather cityWeather);
    }

    public CityWeatherAdapter(Context context, List<CityWeather> cityWeatherList, @LayoutRes int layoutId) {

        this.context = context;
        this.cityWeatherList = cityWeatherList;
        this.layoutId = layoutId;
    }

    @Override
    public CityWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView;
        itemView = inflater.inflate(layoutId, parent, false);

        return new CityWeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CityWeatherViewHolder holder, int position) {

        CityWeather cityWeather = cityWeatherList.get(position);

        holder.cityNameTextView.setText(cityWeather.getName());

        String weatherInCelsius = " - ";

        if (cityWeather.getMain() != null) {

            weatherInCelsius = WeatherUtils.getDegreesRepresentation(context,
                    WeatherUtils.convertToCelsius(cityWeather.getMain().getTemp()));
        }

        holder.cityWeatherDegreesTextView.setText(weatherInCelsius);
        holder.cityWeatherDescriptionTextView.setText(cityWeather.getWeather().get(0).getMain());
        holder.cityWeatherIconImageView.setImageResource(WeatherUtils
                .getArtResourceForWeatherCondition(cityWeather.getWeather().get(0).getId()));

        if (cityWeather.isFavorite()) {

            holder.cityWeatherFavoriteImageView.setImageResource(R.drawable.ic_favorite_white_36dp);
        } else {
            holder.cityWeatherFavoriteImageView.setImageResource(R.drawable.ic_favorite_border_white_36dp);
        }
    }

    @Override
    public int getItemCount() {
        return cityWeatherList.size();
    }

    public List<CityWeather> getCityWeatherList() {
        return cityWeatherList;
    }

    public void addCityWeather(CityWeather cityWeather) {

        int index = cityWeatherList.indexOf(cityWeather);

        // Skip when not found and for the first index
        if (index != -1 && index != 0) {

            CityWeather removed = cityWeatherList.remove(index);
            notifyItemRemoved(index);
            cityWeatherList.add(0, removed);
            notifyItemInserted(0);
        } else if (index == -1) {

            // prepend new cityweather
            cityWeatherList.add(0, cityWeather);
            notifyItemInserted(0);
        }
    }

    public void setCityWeatherList(List<CityWeather> cityWeatherList) {

        this.cityWeatherList = cityWeatherList;
        notifyDataSetChanged();
    }

    public void setOnCityWeatherClickListener(OnCityWeatherClickListener listener) {

        this.clickListener = listener;
    }

    public void setOnCityWeatherFavoriteSelectListener(OnCityWeatherFavoriteSelectListener listener) {

        this.favoriteListener = listener;
    }

    public void clearListeners() {

        favoriteListener = null;
        clickListener = null;
    }

    public class CityWeatherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name_textview)
        TextView cityNameTextView;
        @BindView(R.id.city_weather_degrees_textview)
        TextView cityWeatherDegreesTextView;
        @BindView(R.id.city_weather_description_textview)
        TextView cityWeatherDescriptionTextView;
        @BindView(R.id.city_weather_icon_imageview)
        ImageView cityWeatherIconImageView;
        @BindView(R.id.city_weather_favorite_imageview)
        ImageView cityWeatherFavoriteImageView;


        @OnClick(R.id.city_weather_item_layout)
        public void onCityWeatherItemClicked() {

            if (clickListener != null && cityWeatherList.size() > getAdapterPosition()) {

                clickListener.onCityWeatherClicked(cityWeatherList.get(getAdapterPosition()));
            }
        }

        @OnClick(R.id.city_weather_favorite_imageview)
        public void onCityWeatherFavoriteSelected() {

            CityWeather cityWeather = cityWeatherList.get(getAdapterPosition());

            // TODO: Dont update this immediately this is business logic and should be handled
            // by the presenter
            cityWeather.setFavorite(!cityWeather.isFavorite());

            if (favoriteListener != null && cityWeatherList.size() > getAdapterPosition()) {

                favoriteListener.onCityWeatherFavoriteSelected(cityWeather);
            }

            notifyItemChanged(getAdapterPosition());
        }

        CityWeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

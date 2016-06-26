package com.akoufatzis.weatherapp.model;

import java.util.List;

/**
 * Created by alexk on 02/05/16.
 */
public class CityWeather {

    private long id;
    private String name;
    private List<Weather> weather;
    private Main main;
    private boolean isFavorite;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public City getCity() {

        City city = new City();
        city.setId(id);
        city.setName(name);

        return city;
    }
}

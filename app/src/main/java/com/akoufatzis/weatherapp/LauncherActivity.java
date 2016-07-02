package com.akoufatzis.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.akoufatzis.weatherapp.cityweathersearch.view.CityWeatherSearchActivity;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, CityWeatherSearchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

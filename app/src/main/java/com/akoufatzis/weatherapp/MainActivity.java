package com.akoufatzis.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.akoufatzis.weatherapp.cityweathersearch.view.CitiesWeatherSearchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, CitiesWeatherSearchActivity.class);
        startActivity(intent);
    }
}

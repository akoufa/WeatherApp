package com.akoufatzis.weatherapp.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.akoufatzis.weatherapp.R;

import butterknife.ButterKnife;

/**
 * Created by alexk on 01/05/16.
 */
public abstract class BaseToolbarActivity extends AppCompatActivity {

    protected Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

    }

    protected abstract int getLayoutResourceId();
}

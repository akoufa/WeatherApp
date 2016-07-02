package com.akoufatzis.weatherapp.injection.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexk on 05/05/16.
 */

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {

        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {

        return application;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {

        return application.getApplicationContext();
    }
}

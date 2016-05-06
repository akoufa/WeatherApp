package com.akoufatzis.weatherapp.application.injection.modules;

import com.akoufatzis.weatherapp.communication.GsonCreator;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexk on 05/05/16.
 */
@Module
public class NetworkModule {

    String baseUrl;

    public NetworkModule(String baseUrl) {

        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(loggingInterceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    Gson providesGson() {

        return GsonCreator.createGson();
    }

    @Provides
    @Singleton
    Retrofit providesRestAdapter(OkHttpClient okHttpClient, Gson gson) {

        return new Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}

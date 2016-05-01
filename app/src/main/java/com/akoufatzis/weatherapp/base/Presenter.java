package com.akoufatzis.weatherapp.base;

/**
 * Created by alexk on 01/05/16.
 */
public interface Presenter<V extends MvpView> {

    void attachView(V view);

    void detachView(boolean retainInstance);
}

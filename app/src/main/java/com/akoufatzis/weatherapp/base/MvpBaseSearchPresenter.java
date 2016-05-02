package com.akoufatzis.weatherapp.base;

import rx.Observable;

/**
 * Created by alexk on 02/05/16.
 */
public abstract class MvpBaseSearchPresenter<V extends MvpView> extends MvpBasePresenter<V> {

    public abstract void onSearchTextChanged(Observable<CharSequence> searchObservable);
}

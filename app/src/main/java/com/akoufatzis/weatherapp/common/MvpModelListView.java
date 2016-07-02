package com.akoufatzis.weatherapp.common;

import java.util.List;

/**
 * Created by alexk on 02/05/16.
 */
public interface MvpModelListView<M> extends MvpView {

    void addData(M data);

    void showData(List<M> data);

    void showLoading();

    void hideLoading();

    void showEmpty();

    void showError(Throwable e);
}

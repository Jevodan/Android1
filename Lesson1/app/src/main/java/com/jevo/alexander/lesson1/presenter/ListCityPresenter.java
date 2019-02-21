package com.jevo.alexander.lesson1.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;
import com.jevo.alexander.lesson1.view.city.CityViewFragment;

import java.util.List;

public interface ListCityPresenter extends MvpPresenter<CityViewFragment> {

    void loadInfo(int items, int countItems);

    void writeToBase(List<OneCity> weatherCity);

    List<OneCity> readToBase();
}

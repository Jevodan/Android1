package com.jevo.alexander.lesson1.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.jevo.alexander.lesson1.view.city.CityViewActivity;

public interface CityPresenter extends MvpPresenter<CityViewActivity> {

    void loadInfoSearch(String city);

}

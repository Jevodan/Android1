package com.jevo.alexander.lesson1.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.jevo.alexander.lesson1.view.CityView;

public interface CityPresenter extends MvpPresenter<CityView> {

    void loadInfo();

}

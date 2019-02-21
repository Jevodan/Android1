package com.jevo.alexander.lesson1.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.jevo.alexander.lesson1.model.CityModel;
import com.jevo.alexander.lesson1.model.entity.Cities;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;
import com.jevo.alexander.lesson1.tools.DisposableManager;
import com.jevo.alexander.lesson1.view.city.CityViewActivity;
import com.jevo.alexander.lesson1.view.city.CityViewFragment;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class CityPresenterImpl extends MvpBasePresenter<CityViewActivity> implements CityPresenter {

    final private CityModel model;

    public CityPresenterImpl(CityModel model) {
        this.model = model;
    }

    @Override
    public void destroy() {
        DisposableManager.dispose();
        super.destroy();
    }

   @SuppressWarnings("deprecation")
    @Override
    public void loadInfoSearch(String city) {
        getView().showLoading(false);  // лоадинг
        model.retrieveInfo(city)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OneCity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        DisposableManager.add(d);
                    }

                    @Override
                    public void onNext(OneCity s) {
                        Log.d("ГОРОД НЕКСТ", s.getName());
                        if (isViewAttached()) {
                            getView().setData(s);
                            getView().showContent();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (isViewAttached()) {
                            getView().showError(e, false);
                            Log.e("ERROR", e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.d("ГОРОД", "КОМПЛИТ");
                    }
                });
    }

}

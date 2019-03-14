package com.jevo.alexander.lesson1.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.jevo.alexander.lesson1.App;
import com.jevo.alexander.lesson1.model.CityModel;
import com.jevo.alexander.lesson1.model.entity.weather.ListCities;
import com.jevo.alexander.lesson1.model.entity.weather.Main;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;
import com.jevo.alexander.lesson1.model.entity.weather.Weather;
import com.jevo.alexander.lesson1.model.entity.weather.Wind;
import com.jevo.alexander.lesson1.room.WeatherDao;
import com.jevo.alexander.lesson1.room.database.AppDatabase;

import com.jevo.alexander.lesson1.room.model.WeatherBD;
import com.jevo.alexander.lesson1.tools.Constants;
import com.jevo.alexander.lesson1.tools.DisposableManager;
import com.jevo.alexander.lesson1.view.city.CityViewFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListCityPresenterImpl extends MvpBasePresenter<CityViewFragment> implements ListCityPresenter {

    final private CityModel model;

    public ListCityPresenterImpl(CityModel model) {
        this.model = model;
    }

    @Override
    public void destroy() {
        DisposableManager.dispose();
        super.destroy();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void loadInfo(int items, int countItems) {
        getView().showLoading(false);  // лоадинг
        model.load(items, countItems);
        model.retrieveInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListCities>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        DisposableManager.add(d);
                    }

                    @Override
                    public void onNext(ListCities s) {
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

                    }
                });
    }


    public void writeToBase(List<OneCity> weatherCity) {
        AppDatabase db = App.getInstance().getDatabase();
        WeatherDao weatherDao = db.weatherDao();
        for (OneCity city : weatherCity) {
            Log.d("Запись ГОРОДА в БД", city.getName());
            WeatherBD weatherTable = new WeatherBD();
            weatherTable.id = city.getId();
            weatherTable.name = city.getName();
            weatherTable.desc = city.getWeather().get(0).getDescription();
            weatherTable.icon = city.getWeather().get(0).getIcon();
            weatherTable.humidity = city.getMain().getHumidity();
            weatherTable.pressure = city.getMain().getPressure();
            weatherTable.wind = city.getWind().getSpeed();
            weatherTable.tempMax = city.getMain().getTempMax();
            weatherTable.tempMin = city.getMain().getTempMin();
            weatherDao.insert(weatherTable);
        }
    }

    @Override
    public List<OneCity> readToBase() {
        AppDatabase db = App.getInstance().getDatabase();
        List<OneCity> weatherCity = new ArrayList<>();
        WeatherDao weatherDao = db.weatherDao();
        List<WeatherBD> weathers = weatherDao.getAll();

        for (WeatherBD cityDb : weathers) {
            OneCity city = new OneCity();
            List<Weather> listWeather = new ArrayList<Weather>();
            Main mainWeather = new Main();
            Wind windWeather = new Wind();
            listWeather.add(new Weather());
            city.setWeather(listWeather);
            city.setMain(mainWeather);
            city.setWind(windWeather);

            city.setId((int) cityDb.id);
            city.setName(cityDb.name);
            listWeather.get(0).setDescription(cityDb.desc);
            listWeather.get(0).setIcon(cityDb.icon);
            mainWeather.setHumidity(cityDb.humidity);
            mainWeather.setPressure(cityDb.pressure);
            mainWeather.setHumidity(cityDb.humidity);
            mainWeather.setTempMax(cityDb.tempMax);
            mainWeather.setTempMin(cityDb.tempMin);

            windWeather.setSpeed(cityDb.wind);

            weatherCity.add(city);
        }
        return weatherCity;
    }

}

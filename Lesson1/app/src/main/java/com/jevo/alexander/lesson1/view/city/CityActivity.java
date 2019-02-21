package com.jevo.alexander.lesson1.view.city;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.jevo.alexander.lesson1.R;
import com.jevo.alexander.lesson1.model.CityModelImpl;
import com.jevo.alexander.lesson1.model.api.WeatherService;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;
import com.jevo.alexander.lesson1.presenter.CityPresenter;
import com.jevo.alexander.lesson1.presenter.CityPresenterImpl;
import com.jevo.alexander.lesson1.tools.Constants;
import com.jevo.alexander.lesson1.tools.ItemConvert;
import com.jevo.alexander.lesson1.view.CoatOfArmActivity;
import com.jevo.alexander.lesson1.view.CoatOfArmFragment;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityActivity extends MvpLceActivity<RecyclerView, OneCity, CityViewActivity, CityPresenter>
        implements CityViewActivity, CityItemFragment.OnListFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener, MvpLceView<OneCity> {

    CoatOfArmFragment coatOfArm = new CoatOfArmFragment();
    int ori;
    @BindView(R.id.search_editTtext)
    EditText searchText;

    @OnClick(R.id.search_editTtext)
    public void onClick() {
        loadData(false);
    }

    String searchCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ori = this.getResources().getConfiguration().orientation;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.city_name_fragment, new CityItemFragment())
                .replace(R.id.coat_of_arm_fragment, coatOfArm)
                .commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this,
                        drawer,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onOptionsItemSelected);
    }

    @NonNull
    @Override
    public CityPresenter createPresenter() {
        checkSearch();
        WeatherService api = new Retrofit
                .Builder()
                .baseUrl(WeatherService.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService.class);
        return new CityPresenterImpl(new CityModelImpl(api));
    }

    private void checkSearch() {
        if (searchText != null)
            searchCity = searchText.getText().toString();
        else
            searchCity = "";
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onListFragmentInteraction(OneCity item) {
        Log.d("TAG1", "onListFragmentInteraction: " + item);
        if (ori == 2 && coatOfArm != null) {
            Picasso.get()
                    .load(Constants.iconWeatherExternalLink + item.getWeather().get(0).getIcon() + ".png")
                    .placeholder(R.drawable.cloudy)
                    .into(coatOfArm.image);
            coatOfArm.powTextView.setText(getString(R.string.pow_weather, item.getMain().getPressure().toString()));
            coatOfArm.waterTextView.setText(getString(R.string.water_weather, item.getMain().getHumidity().toString()));
            coatOfArm.windTextView.setText(getString(R.string.wind_weather, item.getWind().getSpeed().toString()));
            coatOfArm.tempTextView.setText(ItemConvert.getTempString(getCurrentFocus(), item));
            coatOfArm.cityTextView.setText(item.getName());
            coatOfArm.descTextView.setText(item.getWeather().get(0).getDescription());
        } else {
            doIntent(item);
        }
    }

    private void doIntent(OneCity item) {
        Intent intent = new Intent(getApplicationContext(), CoatOfArmActivity.class);
        intent.putExtra("one_city", new Gson().toJson(item));
        startActivity(intent);
    }


    //regionMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //endregion
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void setData(OneCity data) {
        doIntent(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        checkSearch();
        getPresenter().loadInfoSearch(searchCity);
    }

}

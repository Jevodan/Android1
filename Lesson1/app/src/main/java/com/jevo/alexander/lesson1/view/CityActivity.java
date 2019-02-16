package com.jevo.alexander.lesson1.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.jevo.alexander.lesson1.R;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;
import com.jevo.alexander.lesson1.tools.Constants;
import com.jevo.alexander.lesson1.tools.ItemConvert;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class CityActivity extends AppCompatActivity
        implements CityItemFragment.OnListFragmentInteractionListener,
        CoatOfArmFragment.CoatOfArmListener,
        NavigationView.OnNavigationItemSelectedListener {

    CoatOfArmFragment coatOfArm = new CoatOfArmFragment();
    int ori;

    @OnTextChanged(R.id.search_editTtext)
    public void onClick() {
        Toast.makeText(this, "В разработке)", Toast.LENGTH_LONG);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CityActivity.this);
        alertDialog.setTitle("Функция поиска города");
        alertDialog.setMessage("В разработке");
        alertDialog.show();
    }

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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("SetTextI18n")
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
            Intent intent = new Intent(getApplicationContext(), CoatOfArmActivity.class);
            intent.putExtra("one_city", new Gson().toJson(item));
            startActivity(intent);
        }
    }

    @Override
    public void clickOnImage() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        }  else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.jevo.alexander.lesson1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jevo.alexander.lesson1.model.CityRepository;
import com.jevo.alexander.lesson1.model.Weather;

import butterknife.ButterKnife;


public class CityActivity extends AppCompatActivity
        implements CityItemFragment.OnListFragmentInteractionListener, CoatOfArmFragment.CoatOfArmListener, NavigationView.OnNavigationItemSelectedListener {

    CoatOfArmFragment coatOfArm;
    CityItemFragment cityName;
    FragmentManager fragmentManager, fragmentManager2;
    int ori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        initModel();

        ori = this.getResources().getConfiguration().orientation;
        fragmentManager = getFragmentManager();
        cityName = new CityItemFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.city_name_fragment, cityName);
        fragmentTransaction.commit();

        coatOfArm = new CoatOfArmFragment();
        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        fragmentTransaction2.replace(R.id.coat_of_arm_fragment, coatOfArm);
        fragmentTransaction2.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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

    private void initModel() {
        Weather weather1 = new Weather("10°С", "773мм", "70%", "14-19м/с");
        Weather weather2 = new Weather("12°С", "573мм", "60%", "2-7м/с");
        Weather weather3 = new Weather("18°С", "373мм", "75%", "5-10м/с");
        Weather weather4 = new Weather("20°С", "173мм", "76%", "3-8м/с");
        Weather weather5 = new Weather("11°С", "873мм", "90%", "24-29м/с");


    }

    @Override
    public void onListFragmentInteraction(CityRepository.OneCity item) {
        Log.d("TAG1", "onListFragmentInteraction: " + item);
        if (ori == 2 && coatOfArm != null) {
            coatOfArm.changeImage(item.imageId);
        } else {
            Intent intent = new Intent(getApplicationContext(), CoatOfArmActivity.class);
            intent.putExtra("id", item.imageId);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
package com.jevo.alexander.lesson1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CityActivity extends AppCompatActivity {

    public static final String CITY = "CITY";
    public static final String TEMP = "TEMP";
    public static final String POW = "POW";
    public static final String WATER = "WATER";
    public static final String WIND = "WIND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        CityItemFragment fragment = new CityItemFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeholder, fragment);
        fragmentTransaction.commit();
    }

}

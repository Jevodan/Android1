package com.jevo.alexander.lesson1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;


import butterknife.ButterKnife;

public class CoatOfArmActivity extends AppCompatActivity implements CoatOfArmFragment.CoatOfArmListener {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coat_of_arm);
        int imageId = getIntent().getIntExtra("id", R.drawable.astana);
        Log.e("TEST", String.valueOf(imageId));
        FragmentManager fragmentManager = getFragmentManager();

        CoatOfArmFragment fragment2 = new CoatOfArmFragment();
        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        fragmentTransaction2.add(R.id.placeholder, fragment2);
        fragmentTransaction2.commit();
        Handler handler = new Handler();
        handler.postDelayed(() -> fragment2.changeImage(imageId), 2000);


    }

    @Override
    public void clickOnImage() {
        finish();
    }
}

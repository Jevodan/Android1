package com.jevo.alexander.lesson1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

public class WeatherActivity extends AppCompatActivity {

    public static final String CITY = "CITY";
    public static final String TEMP = "TEMP";
    public static final String POW = "POW";
    public static final String WATER = "WATER";
    public static final String WIND = "WIND";
    private EditText mCity;
    private Button mButton;
    private CheckBox mTemp;
    private CheckBox mPow;
    private CheckBox mWater;
    private CheckBox mWind;
    private Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        compInit();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, MainActivity.class);
                intent.putExtra(CITY, mCity.getText().toString());
                intent.putExtra(TEMP, mTemp.isChecked() ? "15 C" : "none");
                intent.putExtra(POW, mPow.isChecked() ? "773 mm" : "none");
                intent.putExtra(WATER, mWater.isChecked() ? "80 %" : "none");
                intent.putExtra(WIND, mWind.isChecked() ? "4-9 m/s" : "none");
                startActivity(intent);
            }
        });
    }

    private void compInit() {
        mCity = findViewById(R.id.cityEditText);
        mButton = findViewById(R.id.button);
        mTemp = findViewById(R.id.tempCheckBox);
        mPow = findViewById(R.id.powCheckBox);
        mWater = findViewById(R.id.waterCheckBox);
        mWind = findViewById(R.id.windCheckBox);
        mSwitch = findViewById(R.id.osadkiSwitch);
    }


}

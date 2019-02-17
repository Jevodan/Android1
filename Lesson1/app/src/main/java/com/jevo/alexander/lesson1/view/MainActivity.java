package com.jevo.alexander.lesson1.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jevo.alexander.lesson1.BuildConfig;
import com.jevo.alexander.lesson1.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  public static final String TAGG = "Droid1";
  public static final String CITY = "CITY";
  public static final String TEMP = "TEMP";
  public static final String POW = "POW";
  public static final String WATER = "WATER";
  public static final String WIND = "WIND";
  public static String mes = "";
  String fCity, fTemp, fPow, fWater, fWind;
  TextView cityTextView, tempTextView, powTextView, waterTextView, windTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Intent intent = getIntent();
    fCity = intent.getStringExtra(CITY);
    fTemp = intent.getStringExtra(TEMP);
    fPow = intent.getStringExtra(POW);
    fWater = intent.getStringExtra(WATER);
    fWind = intent.getStringExtra(WIND);
    compInit();

    showToast("CREATE");
  }

  private void compInit() {
    cityTextView = findViewById(R.id.city_field);
    cityTextView.setText(fCity);
    tempTextView = findViewById(R.id.tempTextView);
    powTextView = findViewById(R.id.powTextView);
    waterTextView = findViewById(R.id.waterTextView);
    windTextView = findViewById(R.id.windTextView);

    if (fTemp.equals("none")) tempTextView.setText("");
    else tempTextView.setText("Температура: " + fTemp);

    if (fPow.equals("none")) powTextView.setText("");
    else powTextView.setText("Давление: " + fPow);

    if (fWater.equals("none")) waterTextView.setText("");
    else waterTextView.setText("Влажность: " + fWater);

    if (fWind.equals("none")) windTextView.setText("");
    else windTextView.setText("Ветер: " + fWind);
  }

  @Override
  protected void onPause() {
    super.onPause();
    showToast("PAUSE");
  }

  @Override
  protected void onResume() {
    super.onResume();
    showToast("Resume");
  }

  @Override
  protected void onStart() {
    super.onStart();
    showToast("START");
  }

  @Override
  protected void onStop() {
    super.onStop();
    showToast("STOP");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    showToast("DESTROY");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    showToast("SaveInstance");
  }

  private void showToast(final String message) {
    if (BuildConfig.DEBUG) {
      mes += ", " + message;
      Log.v(TAGG, message);
      Toast toast = Toast.makeText(getApplicationContext(), mes, Toast.LENGTH_LONG);
      toast.show();
      if (message.equals("Resume")) mes = "";
    }
  }

  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // TODO Auto-generated method stub
    Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
    return super.onOptionsItemSelected(item);
  }
}

package com.jevo.alexander.lesson1.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.google.gson.Gson;
import com.jevo.alexander.lesson1.R;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;
import com.jevo.alexander.lesson1.tools.Constants;
import com.jevo.alexander.lesson1.tools.ItemConvert;
import com.squareup.picasso.Picasso;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class CoatOfArmActivity extends AppCompatActivity
    implements CoatOfArmFragment.CoatOfArmListener {
  final CoatOfArmFragment fragment2 = new CoatOfArmFragment();

  @RequiresApi(api = Build.VERSION_CODES.M)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_coat_of_arm);

    OneCity oneCity = new Gson().fromJson(getIntent().getStringExtra("one_city"), OneCity.class);

    int imageId = getIntent().getIntExtra("id", R.drawable.astana);
    getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, fragment2).commit();

    Handler handler = new Handler();
    handler.post(() -> {
   //   fragment2.changeImage(imageId);
      Picasso.get()
              .load(Constants.iconWeatherExternalLink + oneCity.getWeather().get(0).getIcon() + ".png")
              .placeholder(R.drawable.cloudy)
              .into(fragment2.image);
      fragment2.powTextView.setText(getString(R.string.pow_weather, oneCity.getMain().getPressure().toString()));
      fragment2.waterTextView.setText(getString(R.string.water_weather, oneCity.getMain().getHumidity().toString()));
      fragment2.windTextView.setText(getString(R.string.wind_weather, oneCity.getWind().getSpeed().toString()));
      fragment2.tempTextView.setText(ItemConvert.getTempString(fragment2.getView(), oneCity));
      fragment2.cityTextView.setText(oneCity.getName());
      fragment2.descTextView.setText(oneCity.getWeather().get(0).getDescription());
    });

  }

  @Override
  public void clickOnImage() {
    finish();
  }
}

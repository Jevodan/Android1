package com.jevo.alexander.lesson1;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
    int imageId = getIntent().getIntExtra("id", R.drawable.astana);
    getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, fragment2).commit();
    Handler handler = new Handler();
    handler.postDelayed(() -> fragment2.changeImage(imageId), 500);
  }

  @Override
  public void clickOnImage() {
    finish();
  }
}

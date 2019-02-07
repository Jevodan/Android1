package com.jevo.alexander.lesson1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
/*
      // Программно создадим макет
      LinearLayout layout = new LinearLayout(this);
      // сконструируем кастомный элемент
      Preview preview = new Preview(this);
      //Добавим элемент на макет
      Log.d("CustomView", "addView");
      layout.addView(preview);
*/
    final View pre;
    final TextView previewText;
    setContentView(R.layout.activity_start);

    pre = findViewById(R.id.customView);
    previewText = findViewById(R.id.previewText);

    pre.startAnimation(AnimationUtils.loadAnimation(this, R.anim.mycombo));
    previewText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.myscale));
    Handler handler = new Handler();
    handler.postDelayed(() -> goActivity(), 10000);
  }

  private void goActivity() {
    Intent intent = new Intent(getApplicationContext(), CityActivity.class);
    startActivity(intent);
  }
}

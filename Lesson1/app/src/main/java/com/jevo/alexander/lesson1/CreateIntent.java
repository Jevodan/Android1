package com.jevo.alexander.lesson1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Home on 01.11.2017.
 */

public abstract class CreateIntent extends AppCompatActivity {
    public static final String POSITION_CLICK_MENU = "position_click_menu";
    protected abstract Class createClass();


    public Intent newIntent(Context packageContext, int position) {
        Intent intent = new Intent(packageContext, createClass());
        intent.putExtra(POSITION_CLICK_MENU, position);
        return intent;
    }


}

package com.jevo.alexander.lesson1;

import lombok.Data;
import lombok.Getter;

/**
 * Created by Alexander on 16.01.2019.
 */
@Getter
public class CityData {

    public static String[] title = new String[]{
            "Минск",
            "Оберхофф",
            "Тбилиси",
            "Москва",
            "Астана"
    };
    public static int[] picturePath = new int[]{
            R.drawable.belarus,
            R.drawable.european,
            R.drawable.georgia,
            R.drawable.russia,
            R.drawable.kazakhstan
    };
    public static String[] temp = new String[]{
            "10С",
            "12С",
            "18С",
            "11С",
            "21С"
    };
    public static String[] pow = new String[]{
            "773мм",
            "600мм",
            "650мм",
            "680мм",
            "700мм"
    };
    public static String[] water = new String[]{
            "70%",
            "75%",
            "60%",
            "80%",
            "40%"
    };
    public static String[] wind = new String[]{
            "4-9м/с",
            "2-7м/с",
            "1-6м/с",
            "6-11м/с",
            "7-12м/с"
    };

    public static String[] getTitle() {
        return title;
    }

    public static int[] getPicturePath() {
        return picturePath;
    }

    public static String[] getTemp() {
        return temp;
    }

    public static String[] getPow() {
        return pow;
    }

    public static String[] getWater() {
        return water;
    }

    public static String[] getWind() {
        return wind;
    }

}

package com.jevo.alexander.lesson1.model.entity;

import android.os.Build;
import java.util.Arrays;
import java.util.stream.Collectors;
import androidx.annotation.RequiresApi;

public class Cities {
    final public static String[] cities =
            {
                    "520555",
                    "524901",
                    "2013348",
                    "524305",
                    "472757",
                    "473247",
                    "1496747",
                    "491422",
                    "480562",
                    "511565",
                    "1496153",
                    "499099",
                    "1489425",
                    "2023469"
            };

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getCitiesString(int[] array) {
        return Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public static boolean checkLength(int i) {
        return (Cities.cities.length > i) ? false : true;
    }
}

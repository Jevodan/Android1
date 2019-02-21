package com.jevo.alexander.lesson1.tools;


import android.view.View;
import android.widget.ImageView;
import com.jevo.alexander.lesson1.R;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;



public class ItemConvert extends OneCity {

    public static String getTempString(View view, OneCity oneCity) {
        final String temp;
        final long tempMin = Math.round(oneCity.getMain().getTempMin());
        final long tempMax = Math.round(oneCity.getMain().getTempMax());
        if (tempMax == tempMin)
            temp = "    " + ((tempMin > 0) ? "+" : " ") + String.valueOf(tempMax);
        else
            temp = ((tempMin > 0) ? "+" : " ") + tempMin + ((tempMax > 0) ? "+" : " ") + tempMax;
        return view.getContext().getString(R.string.temp, temp);
    }

    public static void getTempImage(View view, OneCity oneCity) {
        final long tempMax = Math.round(oneCity.getMain().getTempMax());
        final ImageView tempImage = view.findViewById(R.id.tempImageView);
        tempImage.setImageResource((tempMax >= 0) ? R.drawable.ic_temperature_hot : R.drawable.ic_temperature);
    }


}

package com.jevo.alexander.lesson1.model;


import android.support.annotation.NonNull;

import com.jevo.alexander.lesson1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CityRepository {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<OneCity> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    private static final Map<Integer, OneCity> ITEM_MAP = new HashMap<>();

    public static int[] picturePath = new int[]{4,10,12,8,19};

    public static String[] weatherPicture = new String[]{
            "clear_night",
            "cloudy",
            "cloudy_night",
            "cold",
            "fog_night",
            "foggy",
            "heat",
            "heavy_cloudy_night",
            "heavy_rain",
            "heavysnow",
            "ice",
            "ice_snow",
            "night_rain",
            "night_rain_thunder",
            "overcast",
            "partly_cloudy",
            "rain",
            "rain_snow",
            "rain_sun",
            "rain_thunder",
            "rain_thunder_sun",
            "rain_tornado",
            "snow",
            "snow_night",
            "snow_sun",
            "snow_thunder_sun",
            "sunny",
            "tornado"
    };

    static {
        // Add some sample items.
        addItem(createOneCity(ITEMS.size(), "Минск"));
        addItem(createOneCity(ITEMS.size(), "Оберхофф"));
        addItem(createOneCity(ITEMS.size(), "Тбилиси"));
        addItem(createOneCity(ITEMS.size(), "Москва"));
        addItem(createOneCity(ITEMS.size(), "Астана"));
    }

    public static int[] getPicturePath() {
        return picturePath;
    }

    private static void addItem(OneCity item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static OneCity createOneCity(int id, String cityName) {
        return new OneCity(id, cityName);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class OneCity {
        public final int id;
        public final int imageId;
        public final String content;

        OneCity(int id, String content) {
            this.id = id;
            this.content = content;
            this.imageId = getImageId(id);
        }

        private int getImageId(int id) {
            int imageId;
            switch (id) {
                case 0:
                    imageId = R.drawable.minsk_coat;
                    break;
                case 1:
                    imageId = R.drawable.ober_coat;
                    break;
                case 2:
                    imageId = R.drawable.georgia_coat;
                    break;
                case 3:
                    imageId = R.drawable.moscow_coat;
                    break;
                case 4:
                    imageId = R.drawable.astana;
                    break;
                default:
                    imageId = R.drawable.moscow_coat;
                    break;
            }
            return imageId;
        }

        @NonNull
        @Override
        public String toString() {
            return "OneCity{" +
                    "id='" + id + '\'' +
                    ", content='" + content + '}';
        }
    }
}

package com.akoufatzis.weatherapp.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.akoufatzis.weatherapp.model.CityWeather;

/**
 * Created by alexk on 02/07/16.
 */
public class Db {

    public Db() {
    }

    public static final class CityWeatherTable {

        public static final String TABLE_NAME = "city_weather";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_FAVORITE = "favorite";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " TEXT PRIMARY KEY," +
                        COLUMN_FAVORITE + " INTEGER NOT NULL" +
                        " );";

        public static ContentValues toContentValues(CityWeather cityWeather) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, cityWeather.getId());
            values.put(COLUMN_FAVORITE, cityWeather.isFavorite() ? 1 : 0);
            return values;
        }

        public static CityWeather parseCursor(@NonNull Cursor cursor) {

            CityWeather cityWeather = new CityWeather();
            cityWeather.setId(Long.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID))));
            cityWeather.setFavorite(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FAVORITE)) == 1);
            return cityWeather;
        }
    }
}

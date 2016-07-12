package com.akoufatzis.weatherapp.data.local;

import android.database.sqlite.SQLiteDatabase;

import com.akoufatzis.weatherapp.model.CityWeather;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by alexk on 02/07/16.
 */
@Singleton
public class DatabaseHelper {

    private final DbOpenHelper dbOpenHelper;

    @Inject
    public DatabaseHelper(DbOpenHelper dbOpenHelper) {

        this.dbOpenHelper = dbOpenHelper;
    }

    // Add CityWeather to Db
    public Observable<Void> addCityWeatherToDb(final CityWeather cityWeather) {

        return Observable.fromCallable(() -> {

            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            db.insert(Db.CityWeatherTable.TABLE_NAME, null, Db.CityWeatherTable.toContentValues(cityWeather));
            return null;
        });
    }

    public Observable<CityWeather> findCityWeatherById(final long id) {

        String sqlQuery = "SELECT * FROM "
                + Db.CityWeatherTable.TABLE_NAME + " WHERE " + Db.CityWeatherTable.COLUMN_ID + "= ?";

        return Observable.fromCallable(() -> {

            SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
            return db.rawQuery(sqlQuery, new String[]{String.valueOf(id)});
        })
                .map((cursor) -> {

                    CityWeather cityWeather = null;

                    if (cursor != null && cursor.moveToFirst()) {
                        cityWeather = Db.CityWeatherTable.parseCursor(cursor);
                        cursor.close();
                    }

                    return cityWeather;
                });
    }

    public Observable<CityWeather> findFavoriteCityWeatherById(final long id) {

        String sqlQuery = "SELECT * FROM "
                + Db.CityWeatherTable.TABLE_NAME + " WHERE " + Db.CityWeatherTable.COLUMN_ID + "= ? AND "
                + Db.CityWeatherTable.COLUMN_FAVORITE + "= ?";

        return Observable.fromCallable(() -> {

            SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
            return db.rawQuery(sqlQuery, new String[]{String.valueOf(id), "1"});
        })
                .map((cursor) -> {

                    CityWeather cityWeather = null;

                    if (cursor != null && cursor.moveToFirst()) {

                        cityWeather = Db.CityWeatherTable.parseCursor(cursor);
                        cursor.close();
                    }

                    return cityWeather;
                });
    }

    public Observable<Void> deleteFavoriteCityWeatherById(final long id) {

        String whereClause = Db.CityWeatherTable.COLUMN_ID + " LIKE ?";

        return Observable.fromCallable(() -> {

            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            db.delete(Db.CityWeatherTable.TABLE_NAME, whereClause, new String[]{String.valueOf(id)});
            return null;
        });
    }

    public Observable<List<CityWeather>> getAllFavoriteCityWeather() {

        String sqlQuery = "SELECT * FROM "
                + Db.CityWeatherTable.TABLE_NAME + " WHERE " + Db.CityWeatherTable.COLUMN_FAVORITE + "= ?";

        return Observable.fromCallable(() -> {

            SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
            return db.rawQuery(sqlQuery, new String[]{String.valueOf(1)});
        }).map(cursor -> {

            List<CityWeather> cityWeatherList = new ArrayList<>();

            if (cursor != null && cursor.moveToFirst()) {

                while (!cursor.isAfterLast()) {

                    cityWeatherList.add(Db.CityWeatherTable.parseCursor(cursor));
                    cursor.moveToNext();
                }

                cursor.close();
            }

            return cityWeatherList;
        });
    }
}

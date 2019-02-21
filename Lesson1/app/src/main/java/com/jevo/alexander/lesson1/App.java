package com.jevo.alexander.lesson1;

import android.app.Application;

import com.jevo.alexander.lesson1.room.database.AppDatabase;

import androidx.room.Room;

public class App extends Application {

    public static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                //TODO: Запросы асинхронно в другом потоке
                .allowMainThreadQueries() // пока из основного потка...
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

}

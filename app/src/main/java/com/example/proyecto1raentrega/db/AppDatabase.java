package com.example.proyecto1raentrega.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.proyecto1raentrega.models.ColeccionEntity;
import com.example.proyecto1raentrega.models.PeliculaEntity;

@Database(entities = {PeliculaEntity.class, ColeccionEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract PeliculaDao peliculaDao();
    public abstract ColeccionDao coleccionDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "movie_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
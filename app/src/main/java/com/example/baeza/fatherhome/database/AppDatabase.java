package com.example.baeza.fatherhome.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {FavoriteEntry.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "favoritelist";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        //Queries should be done in a separate thread to avoid locking the UI
                        //allows this TEMPORALLY  to see if the db is working
                       // .allowMainThreadQueries()
                        .build();
            }
    }
    return sInstance;
    }
    public abstract FavoriteDao mFavoriteDao();
}

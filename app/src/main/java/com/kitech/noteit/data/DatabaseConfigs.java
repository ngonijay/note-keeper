package com.kitech.noteit.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kitech.noteit.domain.NoteEntity;

@Database(entities = {NoteEntity.class}, version = 2)
public abstract class DatabaseConfigs extends RoomDatabase {
    private static final String DB_NAME = "notes_db";
    private  static  DatabaseConfigs instance;

    public  static synchronized  DatabaseConfigs getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DatabaseConfigs.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
        }

    public abstract NoteDao noteDao();
}

package com.kitech.noteit.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kitech.noteit.domain.NoteEntity;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * from notes")
    LiveData<List<NoteEntity>> getAll();

    @Insert
    void insertNote(NoteEntity note);
}

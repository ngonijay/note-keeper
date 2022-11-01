package com.kitech.noteit.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kitech.noteit.domain.NoteEntity;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * from notes where id=:id")
    LiveData<NoteEntity> getNoteById(long id);

    @Query("SELECT * from notes")
    LiveData<List<NoteEntity>> getAll();

    @Insert
    void insertNote(NoteEntity note);

    @Update
    void updateNote(NoteEntity noteEntity);

    @Query("Delete from notes")
    void deleteAll();

    @Query("Delete from notes where id=:id")
    void deleteNoteById(long id);
}

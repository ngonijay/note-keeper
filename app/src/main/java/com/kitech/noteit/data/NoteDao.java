package com.kitech.noteit.data;

import androidx.room.Dao;
import androidx.room.Insert;

import com.kitech.noteit.domain.NoteEntity;

@Dao
public interface NoteDao {

    @Insert
    void insertNote(NoteEntity note);
}

package com.kitech.noteit.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.kitech.noteit.data.DatabaseConfigs;
import com.kitech.noteit.domain.NoteEntity;

public class CreateNoteViewModel extends AndroidViewModel {

    private final DatabaseConfigs db;

    public CreateNoteViewModel(@NonNull Application application) {
        super(application);
        db  = DatabaseConfigs.getInstance(application);
    }

    public void saveNote(NoteEntity note){
        db.noteDao().insertNote(note);

    }
}
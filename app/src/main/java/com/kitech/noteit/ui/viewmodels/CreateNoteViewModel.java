package com.kitech.noteit.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.kitech.noteit.data.DatabaseConfigs;
import com.kitech.noteit.data.NoteDao;
import com.kitech.noteit.domain.NoteEntity;

public class CreateNoteViewModel extends AndroidViewModel {

    private final DatabaseConfigs db;
    private final NoteDao noteDao;

    public CreateNoteViewModel(@NonNull Application application) {
        super(application);
        db  = DatabaseConfigs.getInstance(application);
        noteDao = db.noteDao();
    }

    public void saveNote(NoteEntity note){
        noteDao.insertNote(note);
    }
}
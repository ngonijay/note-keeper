package com.kitech.noteit.ui.Notes.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kitech.noteit.data.DatabaseConfigs;
import com.kitech.noteit.data.NoteDao;
import com.kitech.noteit.domain.NoteEntity;

import java.util.List;

public class CreateNoteViewModel extends AndroidViewModel {

    private final DatabaseConfigs db;
    private final NoteDao noteDao;
    private LiveData<NoteEntity> mNotes;

    public CreateNoteViewModel(@NonNull Application application) {
        super(application);
        db  = DatabaseConfigs.getInstance(application);
        noteDao = db.noteDao();
    }
    
    public LiveData<NoteEntity> getNoteById(long id){
        return mNotes =  noteDao.getNoteById(id);
    }

    public void saveNote(NoteEntity note){
        noteDao.insertNote(note);
    }

    public void updateNote(NoteEntity noteEntity) {
        noteDao.updateNote(noteEntity);
    }
}
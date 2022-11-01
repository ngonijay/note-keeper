package com.kitech.noteit.ui.Notes.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kitech.noteit.data.DatabaseConfigs;
import com.kitech.noteit.data.NoteDao;
import com.kitech.noteit.domain.NoteEntity;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private final NoteDao notesDao;
    private LiveData<List<NoteEntity>> mNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        DatabaseConfigs db = DatabaseConfigs.getInstance(application);
        notesDao = db.noteDao();
    }



    public LiveData<List<NoteEntity>> getAllNotes(){
        return mNotes =  notesDao.getAll();
    }

    public void deleteAll() {
        notesDao.deleteAll();
    }
}

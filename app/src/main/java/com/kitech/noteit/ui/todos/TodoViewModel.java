package com.kitech.noteit.ui.todos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.kitech.noteit.data.DatabaseConfigs;
import com.kitech.noteit.data.TodoDao;
import com.kitech.noteit.domain.TodoEntity;

public class TodoViewModel extends AndroidViewModel {

    private DatabaseConfigs db;
    private TodoDao todoDao;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        db = DatabaseConfigs.getInstance(application);
    }

    public void saveTodo (TodoEntity todo){
        try {
            db.todoDao().insertTodo(todo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

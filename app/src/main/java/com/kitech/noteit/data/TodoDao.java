package com.kitech.noteit.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kitech.noteit.domain.NoteEntity;
import com.kitech.noteit.domain.TodoEntity;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * from todos where id=:id")
    LiveData<TodoEntity> getTodoById(long id);

    @Query("SELECT * from todos")
    LiveData<List<TodoEntity>> getAll();

    @Insert
    void insertTodo(TodoEntity todo);

    @Update
    void updateTodo(TodoEntity todoEntity);

    @Query("Delete from todos")
    void deleteAll();

    @Query("Delete from todos where id=:id")
    void deleteTodoById(long id);
}

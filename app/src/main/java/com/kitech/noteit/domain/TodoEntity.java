package com.kitech.noteit.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "todos")
public class TodoEntity {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "todo_message")
    private String todoMessage;

    @ColumnInfo(name = "todo_date_time")
    private LocalDateTime todoDateTime;

    @ColumnInfo(name = "todo_priority")
    private Boolean todoPriority;

    public String getTodoMessage() {
        return todoMessage;
    }

    public void setTodoMessage(String todoMessage) {
        this.todoMessage = todoMessage;
    }

    public LocalDateTime getTodoDateTime() {
        return todoDateTime;
    }

    public void setTodoDateTime(LocalDateTime todoDateTime) {
        this.todoDateTime = todoDateTime;
    }

    public Boolean getTodoPriority() {
        return todoPriority;
    }

    public void setTodoPriority(Boolean todoPriority) {
        this.todoPriority = todoPriority;
    }
}

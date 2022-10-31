package com.kitech.noteit.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class NoteEntity {


    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "first_title")
    private String noteTitle;

    @ColumnInfo(name = "note_created_date")
    private String noteCreatedDate;

    @ColumnInfo(name = "note_message")
    private String noteMessage;

    @ColumnInfo(name = "note_modified_date")
    private String noteModifiedDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteCreatedDate() {
        return noteCreatedDate;
    }

    public void setNoteCreatedDate(String noteCreatedDate) {
        this.noteCreatedDate = noteCreatedDate;
    }

    public String getNoteMessage() {
        return noteMessage;
    }

    public void setNoteMessage(String noteMessage) {
        this.noteMessage = noteMessage;
    }

    public String getNoteModifiedDate() {
        return noteModifiedDate;
    }

    public void setNoteModifiedDate(String noteModifiedDate) {
        this.noteModifiedDate = noteModifiedDate;
    }
}

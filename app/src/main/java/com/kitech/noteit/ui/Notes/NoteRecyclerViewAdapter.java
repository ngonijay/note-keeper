package com.kitech.noteit.ui.Notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kitech.noteit.R;
import com.kitech.noteit.domain.NoteEntity;

import java.util.ArrayList;
import java.util.List;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder> {
    private List<NoteEntity> myNotes;

    public NoteRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public NoteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            NoteEntity note = myNotes.get(position);
            holder.noteTitle.setText(note.getNoteTitle());
            holder.noteCreatedDate.setText(note.getNoteCreatedDate());
    }
    @Override
    public int getItemCount() {
        return myNotes.size();
    }

    public void setNotes(List<NoteEntity> notes) {
        myNotes = new ArrayList<>(notes);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView noteTitle, noteCreatedDate,noteMessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitleTxt);
            noteCreatedDate = itemView.findViewById(R.id.noteCreatedDate);
        }


    }
}

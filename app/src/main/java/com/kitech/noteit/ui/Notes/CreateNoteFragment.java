package com.kitech.noteit.ui.Notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kitech.noteit.R;
import com.kitech.noteit.databinding.FragmentCreateNoteBinding;
import com.kitech.noteit.domain.NoteEntity;
import com.kitech.noteit.ui.Notes.viewmodels.CreateNoteViewModel;
import com.kitech.noteit.utils.CONSTANTS;
import com.kitech.noteit.utils.CustomDateTimeMethods;

import java.util.Objects;

public class CreateNoteFragment extends Fragment {

    private CreateNoteViewModel mViewModel;
    private FragmentCreateNoteBinding mBinding;
    private long mNoteId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentCreateNoteBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(CreateNoteViewModel.class);
        return mBinding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            mNoteId = getArguments().getLong(CONSTANTS.NOTE_ID);
        }
        initializeView();
        mBinding.noteCreatedTimeTxt.setText(CustomDateTimeMethods.getCurrentLocalDateTime());
    }

    private void initializeView() {

        if (mNoteId != 0)
            prepopulateNoteFields(mNoteId);

        mBinding.toolbar.generalToolbar.setTitle(R.string.add_note);
        mBinding.toolbar.generalToolbar.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_save_note)
                    processNote();
                if (menuItem.getItemId() == R.id.action_delete_note)
                    deleteNote();
                return false;
            }
        });

    }

    private void deleteNote() {
        if (mNoteId != 0){
            mViewModel.deleteNote(mNoteId);
            Toast.makeText(getContext(), "note deleted", Toast.LENGTH_SHORT).show();
            requireActivity().onBackPressed();
        }

    }

    private void processNote() {

        if (mNoteId != 0)
            updateNote(mNoteId);
        else
            saveNoteIn();
    }

    private void saveNoteIn() {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setNoteTitle(Objects.requireNonNull(mBinding.noteTitleTextEditText.getText()).toString());
        noteEntity.setNoteMessage(Objects.requireNonNull(mBinding.noteMessageTextEditText.getText()).toString());
        noteEntity.setNoteCreatedDate(Objects.requireNonNull(mBinding.noteCreatedTimeTxt.getText()).toString());
        noteEntity.setNoteModifiedDate("");

        mViewModel.saveNote(noteEntity);
        Toast.makeText(getContext(), R.string.note_saved_message, Toast.LENGTH_SHORT).show();

        requireActivity().onBackPressed();
    }

    private void prepopulateNoteFields(long id) {
        mViewModel.getNoteById(id).observe(getViewLifecycleOwner(), note -> {
            mBinding.noteTitleTextEditText.setText(note.getNoteTitle());
            mBinding.noteCreatedTimeTxt.setText(note.getNoteCreatedDate());
            mBinding.noteMessageTextEditText.setText(note.getNoteMessage());
        });
    }

    private void updateNote(long id) {
        mViewModel.getNoteById(id).observe(getViewLifecycleOwner(), note -> {
            if (note != null) {
                note.setNoteTitle(Objects.requireNonNull(mBinding.noteTitleTextEditText.getText()).toString());
                note.setNoteCreatedDate(mBinding.noteCreatedTimeTxt.getText().toString());
                note.setNoteMessage(Objects.requireNonNull(mBinding.noteMessageTextEditText.getText()).toString());
                mViewModel.updateNote(note);
                requireActivity().onBackPressed();
            }
        });
    }



}

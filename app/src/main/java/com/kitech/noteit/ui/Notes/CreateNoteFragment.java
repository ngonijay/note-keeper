package com.kitech.noteit.ui.Notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kitech.noteit.R;
import com.kitech.noteit.databinding.FragmentCreateNoteBinding;
import com.kitech.noteit.domain.NoteEntity;
import com.kitech.noteit.ui.Notes.viewmodels.CreateNoteViewModel;
import com.kitech.noteit.utils.CustomDateTimeMethods;

import java.util.Objects;

public class CreateNoteFragment extends Fragment {

    private CreateNoteViewModel mViewModel;
    private FragmentCreateNoteBinding mBinding;

    public static CreateNoteFragment newInstance() {
        return new CreateNoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentCreateNoteBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(CreateNoteViewModel.class);
        return mBinding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
        mBinding.noteCreatedTimeTxt.setText(CustomDateTimeMethods.getCurrentLocalDateTime());
    }

    private void saveNoteInDb() {
        NoteEntity noteEntity = new NoteEntity();
            noteEntity.setNoteTitle(Objects.requireNonNull(mBinding.noteTitleTextEditText.getText()).toString());
            noteEntity.setNoteMessage(Objects.requireNonNull(mBinding.noteMessageTextEditText.getText()).toString());
            noteEntity.setNoteCreatedDate(Objects.requireNonNull(mBinding.noteCreatedTimeTxt.getText()).toString());
            noteEntity.setNoteModifiedDate("");
        mViewModel.saveNote(noteEntity);

        Toast.makeText(getContext(), "SAVED", Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();

    }

    private void initializeView() {

        if( getArguments() != null){
        long id = getArguments().getLong("NOTE_ID");
        if (id != 0)
            getNoteDetails(id);

            mBinding.saveButton.setOnClickListener( v -> updateNote(id));
            mBinding.saveButton.setText("UPDATE");
        } else {
            mBinding.saveButton.setText("SAVE");
            mBinding.saveButton.setOnClickListener(v -> saveNoteInDb());
        }

    }

    private void updateNote(long id) {
        mViewModel.getNoteById(id).observe(getViewLifecycleOwner(), note -> {
            note.setNoteTitle(mBinding.noteTitleTextEditText.getText().toString());
            note.setNoteCreatedDate(mBinding.noteCreatedTimeTxt.getText().toString());
            note.setNoteMessage(mBinding.noteMessageTextEditText.getText().toString());

            mViewModel.updateNote(note);
            getActivity().onBackPressed();
        });
    }

    private void getNoteDetails(long id) {
        mViewModel.getNoteById(id).observe(getViewLifecycleOwner(), note ->{
            mBinding.noteTitleTextEditText.setText(note.getNoteTitle());
            mBinding.noteCreatedTimeTxt.setText(note.getNoteCreatedDate());
            mBinding.noteMessageTextEditText.setText(note.getNoteMessage());
        });
    }

}

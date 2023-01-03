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
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.kitech.noteit.R;
import com.kitech.noteit.databinding.FragmentNotesBinding;
import com.kitech.noteit.domain.NoteEntity;
import com.kitech.noteit.ui.Notes.viewmodels.NotesViewModel;
import com.kitech.noteit.utils.CONSTANTS;

import java.util.Collections;
import java.util.List;

public class NotesFragment extends Fragment  {

    private FragmentNotesBinding binding;
    private NotesViewModel mViewModel;
    private NoteRecyclerViewAdapter mNotesAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNotesBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeView();
        getNotes();

        binding.addNewNoteButton.setOnClickListener(view1 -> NavHostFragment.findNavController(NotesFragment.this)
                .navigate(R.id.action_NotesFragment_to_createNoteFragment));

    }

    private void initializeView() {

        binding.toolbar.setTitle(R.string.notes_fragment_label);
        binding.toolbar.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.note_fragement_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_delete_all_notes){
                    mViewModel.deleteAll();
                    getNotes();

                }

                return false;
            }
        });
    }


    private void getNotes() {
        mViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes ->{
            if (!notes.isEmpty())
                loadNotes(notes);
            else{
                loadNotes(Collections.emptyList());
                Toast.makeText(getContext(), R.string.no_notes_found, Toast.LENGTH_SHORT).show();}
        });
    }

    private void loadNotes(List<NoteEntity> notes) {
        mNotesAdapter = new NoteRecyclerViewAdapter(this::viewNoteDetails);
        binding.notesRecyclerView.setAdapter(mNotesAdapter);
        mNotesAdapter.setNotes(notes);
    }

    private void viewNoteDetails(long noteId) {
        Bundle bundle = new Bundle();
        bundle.putLong(CONSTANTS.NOTE_ID, noteId);
        NavHostFragment.findNavController(NotesFragment.this)
                .navigate(R.id.action_NotesFragment_to_createNoteFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
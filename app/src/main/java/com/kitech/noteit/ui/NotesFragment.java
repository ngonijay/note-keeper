package com.kitech.noteit.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.kitech.noteit.R;
import com.kitech.noteit.databinding.FragmentCreateNoteBinding;
import com.kitech.noteit.databinding.FragmentNotesBinding;
import com.kitech.noteit.ui.viewmodels.CreateNoteViewModel;
import com.kitech.noteit.ui.viewmodels.NotesViewModel;

public class NotesFragment extends Fragment {

    private FragmentNotesBinding binding;
    private NotesViewModel mViewModel;

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



        binding.buttonFirst.setOnClickListener(view12 -> NavHostFragment.findNavController(NotesFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment));

        binding.fab.setOnClickListener(view1 -> NavHostFragment.findNavController(NotesFragment.this)
                .navigate(R.id.action_NotesFragment_to_createNoteFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
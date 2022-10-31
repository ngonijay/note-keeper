package com.kitech.noteit;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kitech.noteit.databinding.FragmentCreateNoteBinding;
import com.kitech.noteit.databinding.FragmentNotesBinding;
import com.kitech.noteit.utils.CustomDateTimeMethods;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
        return mBinding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.noteCreatedTimeTxt.setText(CustomDateTimeMethods.getCurrentLocalDateTime());
    }
}

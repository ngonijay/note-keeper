package com.kitech.noteit.ui.todos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kitech.noteit.databinding.FragmentTodoDialogListDialogBinding;
import com.kitech.noteit.domain.TodoEntity;
import com.kitech.noteit.ui.Notes.viewmodels.CreateNoteViewModel;
import com.kitech.noteit.utils.CustomDateTimePicker;

import java.util.Objects;


/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     TodoDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class TodoDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";
    private FragmentTodoDialogListDialogBinding binding;
    private TodoViewModel todoViewModel;

    // TODO: Customize parameters
    public static TodoDialogFragment newInstance(int itemCount) {
        final TodoDialogFragment fragment = new TodoDialogFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        binding = FragmentTodoDialogListDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewInit();
    }

    private void viewInit() {
        binding.saveTodoButton.setOnClickListener(view ->{
           saveTodo(validateTodoFields());
        });

        binding.totDoTimePicker.setOnClickListener(view ->{

                DialogFragment newFragment = new CustomDateTimePicker();
                newFragment.show(getChildFragmentManager(), "timePicker");

        });

    }

    private void saveTodo(boolean isFieldValid) {
        if (isFieldValid){
            //todo:save todo
            TodoEntity todo = new TodoEntity();
            todo.setTodoMessage(binding.todoTextEditText.getText().toString());
            todo.getTodoDateTime(binding.totDoTimePicker.)
            todoViewModel.saveTodo();
        } else{
            //todo:show message
        }
    }

    private boolean validateTodoFields() {
        //check if fields are populated
        return !Objects.requireNonNull(binding.todoTextEditText.getText()).toString().isEmpty();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
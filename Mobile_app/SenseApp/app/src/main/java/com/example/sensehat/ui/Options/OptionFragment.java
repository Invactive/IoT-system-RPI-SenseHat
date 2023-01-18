package com.example.sensehat.ui.Options;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.sensehat.R;
import com.example.sensehat.databinding.FragmentOptionBinding;

public class OptionFragment extends Fragment {

    private FragmentOptionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OptionViewModel DataViewModel =
                new ViewModelProvider(this).get(OptionViewModel.class);

        binding = FragmentOptionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textOption;
        DataViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.sensehat.ui.leds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.sensehat.databinding.FragmentLedsBinding;

public class LedsFragment extends Fragment {

    private FragmentLedsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LedsViewModel galleryViewModel =
                new ViewModelProvider(this).get(LedsViewModel.class);

        binding = FragmentLedsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textLeds;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
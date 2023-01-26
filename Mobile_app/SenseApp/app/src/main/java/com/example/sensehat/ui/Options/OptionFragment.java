package com.example.sensehat.ui.Options;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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

        EditText chartInterval = binding.etChartsInterval;
        EditText tableInterval = binding.etTableInterval;
        EditText serverIP = binding.etIpAddress;
        Button submit = binding.submitBtn;

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!chartInterval.getText().toString().equals("")){
                    OptionViewModel.setChartInterv(Integer.parseInt(chartInterval.getText().toString()));
                }
                if(!tableInterval.getText().toString().equals("")){
                    OptionViewModel.setTableInterv(Integer.parseInt(tableInterval.getText().toString()));
                }
                if(!serverIP.getText().toString().equals("")){
                    OptionViewModel.setServerIP(serverIP.getText().toString());
                }
            }});

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
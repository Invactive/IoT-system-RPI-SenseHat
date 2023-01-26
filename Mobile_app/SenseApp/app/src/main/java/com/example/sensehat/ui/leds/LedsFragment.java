package com.example.sensehat.ui.leds;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sensehat.MainActivity;
import com.example.sensehat.databinding.FragmentLedsBinding;
import com.example.sensehat.ui.charts.ChartsViewModel;

public class LedsFragment extends Fragment {

    private FragmentLedsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LedsViewModel LedsViewModel =
                new ViewModelProvider(requireActivity()).get(LedsViewModel.class);

        binding = FragmentLedsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        LedsViewModel viewModel = new ViewModelProvider(this).get(LedsViewModel.class);

        //binding layout
        final GridLayout gridLayout = binding.gridLayout;
        final Button reset = binding.reset;
        final TextView r = binding.rVal;
        final TextView g = binding.gVal;
        final TextView b = binding.bVal;




        //append buttons to grid to create 8x8 matrix
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Button button = new Button(requireContext());
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.height = GridLayout.LayoutParams.WRAP_CONTENT;
                params.columnSpec = GridLayout.spec(j);
                params.rowSpec = GridLayout.spec(i);
                params.width = 115;
                button.setLayoutParams(params);
                button.setBackgroundTintList(ColorStateList.valueOf(Color.argb(150,  0, 0, 0)));
                button.setId(i * 8 + j);
                gridLayout.addView(button);
            }
            }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Button button = (Button) gridLayout.getChildAt(i * 8 + j);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)
                    {
                        int index = button.getId();
                        int argX = index % 8;
                        int argY = (index - argX) / 8;
                        int argR = 0;
                        int argG = 0;
                        int argB = 0;

                        if(!r.getText().toString().equals("")){
                            argR = Integer.parseInt(r.getText().toString());
                        }
                        if(!g.getText().toString().equals("")){
                            argG = Integer.parseInt(g.getText().toString());
                        }
                        if(!b.getText().toString().equals("")){
                            argB = Integer.parseInt(b.getText().toString());
                        }

                        viewModel.setLeds(argX, argY, argR, argG, argB);

                    }
                });
            }
        }


        LedsViewModel.getText().observe(getViewLifecycleOwner(), data -> {
            for(int x =0 ; x<64; x++){
                int argX = x % 8;
                int argY = (x - argX) / 8;
                Button button = (Button) gridLayout.getChildAt(argY * 8 + argX);
                button.setBackgroundTintList(ColorStateList.valueOf(Color.argb(150,  data.get(x).get(0), data.get(x).get(1), data.get(x).get(2))));
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                viewModel.reset();
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
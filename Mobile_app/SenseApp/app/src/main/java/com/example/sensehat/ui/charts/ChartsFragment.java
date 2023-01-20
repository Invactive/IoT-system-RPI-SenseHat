package com.example.sensehat.ui.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sensehat.databinding.FragmentChartsBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FileUtils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChartsFragment extends Fragment {
    private ChartsViewModel mViewModel;
    private FragmentChartsBinding binding;
    private int i = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChartsViewModel ChartsViewModel =
                new ViewModelProvider(this).get(ChartsViewModel.class);

        binding = FragmentChartsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Zbindowanie wykresu z xml
        LineChart chart = (LineChart) binding.chart;
        chart.setBackgroundColor(Color.LTGRAY);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setGranularityEnabled(true);
        leftAxis.setAxisMinimum(-30f);
        leftAxis.setAxisMaximum(110f);
        leftAxis.setYOffset(-9f);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        LineData data = new LineData();
        chart.setData(data);
        ArrayList<Entry> values = new ArrayList<>();
        LineDataSet set = new LineDataSet(values, "Temperature");
        set.setLineWidth(2.5f);
        set.setColor(Color.RED);
        data.addDataSet(set);

        mViewModel = new ViewModelProvider(this).get(ChartsViewModel.class);
        mViewModel.getData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                set.addEntry(new Entry(i, Float.parseFloat(s)));
                if(set.getEntryCount() > 50){
                    Entry e = set.getEntryForXValue(set.getEntryCount() - 50, Float.NaN);
                    data.removeEntry(e, 0);
                }
                data.notifyDataChanged();
                chart.notifyDataSetChanged();
                chart.invalidate();
                i++;
            }
        });


        // Opis osi X

//        final TextView textView = binding.textCharts;
//        ChartsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
package com.example.sensehat.ui.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sensehat.databinding.FragmentChartsBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ChartsFragment extends Fragment {

    private FragmentChartsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChartsViewModel ChartsViewModel =
                new ViewModelProvider(this).get(ChartsViewModel.class);

        binding = FragmentChartsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        LineChart chart = (LineChart) binding.chart;

        ArrayList<String> xAxisValues = new ArrayList<String>();
        xAxisValues.add("Term1");
        xAxisValues.add("Term2");
        xAxisValues.add("Term3");
        xAxisValues.add("Term4");
        xAxisValues.add("Term5");
        xAxisValues.add("Term6");

        chart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));


        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(0, 60));//20    2018f  (2018f, 20))
        entries.add(new Entry(1, 57));//40    2019f   (2019f, 40))
        entries.add(new Entry(2, 65));//60    2020f (2020f, 60))
        entries.add(new Entry(3, 70));//80    2021f (2021f, 80))
        entries.add(new Entry(4, 80));//80    2021f (2021f, 80))
        entries.add(new Entry(5, 70));//80    2021f (2021f, 80))


        ArrayList<Entry> entry = new ArrayList<>();
        entry.add(new Entry(0, 70));
        entry.add(new Entry(1, 50));
        entry.add(new Entry(2, 60));
        entry.add(new Entry(3, 65));
        entry.add(new Entry(4, 75));
        entry.add(new Entry(5, 80));

        ArrayList<Entry> marathi = new ArrayList<>();
        marathi.add(new Entry(0, 80));
        marathi.add(new Entry(1, 70));
        marathi.add(new Entry(2, 50));
        marathi.add(new Entry(3, 68));
        marathi.add(new Entry(4, 55));
        marathi.add(new Entry(5, 36));


        ArrayList<LineDataSet> lines = new ArrayList<LineDataSet>();

        LineDataSet set1 = new LineDataSet(entries, "English");
        set1.setDrawFilled(true);
        set1.setFillColor(Color.WHITE);
        set1.setColor(Color.RED);
        set1.setCircleColor(Color.DKGRAY);
        lines.add(set1);

        LineDataSet set2 = new LineDataSet(entry, "Hindi");
        set2.setDrawFilled(true);
        set2.setFillColor(Color.WHITE);
        set2.setColor(Color.BLUE);
        set2.setCircleColor(Color.RED);
        lines.add(set2);

        LineDataSet set3 = new LineDataSet(marathi, "Marathi");
        set3.setDrawFilled(true);
        set3.setFillColor(Color.WHITE);
        set3.setColor(Color.YELLOW);
        set3.setCircleColor(Color.parseColor("#8B0000"));
        lines.add(set3);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);

        chart.setData(new LineData(dataSets));

        chart.getDescription().setText("");

        chart.getDescription().setTextColor(Color.RED);


        chart.animateY(1400, Easing.EaseInOutBounce);
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
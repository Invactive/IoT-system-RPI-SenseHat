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
import com.github.mikephil.charting.components.Legend;
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
    private int ttc = 0;
    private int ttf = 0;
    private int tph = 0;
    private int tpm = 0;
    private int th = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChartsViewModel ChartsViewModel =
                new ViewModelProvider(this).get(ChartsViewModel.class);

        binding = FragmentChartsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Zbindowanie wykresu z xml
        LineChart chart = (LineChart) binding.chart;
        chart.setBackgroundColor(Color.WHITE);
        Legend legend = chart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setGranularityEnabled(true);
//        leftAxis.setAxisMinimum(-30f);
//        leftAxis.setAxisMaximum(110f);
//        leftAxis.setYOffset(-9f);
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        LineData data = new LineData(dataSets);
        chart.setData(data);

        mViewModel = new ViewModelProvider(this).get(ChartsViewModel.class);
        updater(mViewModel, "tempC", chart, data, dataSets);
        updater(mViewModel, "tempF", chart, data, dataSets);
        updater(mViewModel, "pressHpa", chart, data, dataSets);
        updater(mViewModel, "pressMmhg", chart, data, dataSets);
        updater(mViewModel, "humidity", chart, data, dataSets);


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

    public void updater(ChartsViewModel model, String type, LineChart chart,LineData data, ArrayList<ILineDataSet> dataSets){

        if(type == "tempC") {
            ArrayList<Entry> values = new ArrayList<>();
            LineDataSet set = new LineDataSet(values, "Temperature Celcius");
            set.setDrawValues(false);
            set.setDrawCircles(false);
            set.setLineWidth(2.5f);
            set.setColor(Color.RED);
            dataSets.add(set);

            model.getTempCData().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    set.addEntry(new Entry(ttc, Float.parseFloat(s)));
                    if (set.getEntryCount() > 50) {
                        Entry e = set.getEntryForXValue(set.getEntryCount() - 50, Float.NaN);
                        int index = dataSets.indexOf(set);
                        data.removeEntry(e, index);
                    }
                    data.notifyDataChanged();
                    chart.notifyDataSetChanged();
                    chart.invalidate();
                    ttc++;
                }
            });
        }
        if(type == "tempF") {
            ArrayList<Entry> values = new ArrayList<>();
            LineDataSet set = new LineDataSet(values, "Temperature Fahrenheit");
            set.setDrawValues(false);
            set.setDrawCircles(false);
            set.setLineWidth(2.5f);
            set.setColor(Color.BLUE);
            dataSets.add(set);

            model.getTempFData().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    set.addEntry(new Entry(ttf, Float.parseFloat(s)));
                    if (set.getEntryCount() > 50) {
                        Entry e = set.getEntryForXValue(set.getEntryCount() - 50, Float.NaN);
                        int index = dataSets.indexOf(set);
                        data.removeEntry(e, index);
                    }
                    data.notifyDataChanged();
                    chart.notifyDataSetChanged();
                    chart.invalidate();
                    ttf++;
                }
            });
        }
        if(type == "pressHpa") {
            ArrayList<Entry> values = new ArrayList<>();
            LineDataSet set = new LineDataSet(values, "Pressure Hpa");
            set.setDrawValues(false);
            set.setDrawCircles(false);
            set.setLineWidth(2.5f);
            set.setColor(Color.GREEN);
            dataSets.add(set);

            model.getPressHpaData().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    set.addEntry(new Entry(tph, Float.parseFloat(s)));
                    if (set.getEntryCount() > 50) {
                        Entry e = set.getEntryForXValue(set.getEntryCount() - 50, Float.NaN);
                        int index = dataSets.indexOf(set);
                        data.removeEntry(e, index);
                    }
                    data.notifyDataChanged();
                    chart.notifyDataSetChanged();
                    chart.invalidate();
                    tph++;
                }
            });
        }
        if(type == "pressMmhg") {
            ArrayList<Entry> values = new ArrayList<>();
            LineDataSet set = new LineDataSet(values, "Pressure Mmhg");
            set.setDrawValues(false);
            set.setDrawCircles(false);
            set.setLineWidth(2.5f);
            set.setColor(Color.GRAY);
            dataSets.add(set);

            model.getPressMmhgData().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    set.addEntry(new Entry(tpm, Float.parseFloat(s)));
                    if (set.getEntryCount() > 50) {
                        Entry e = set.getEntryForXValue(set.getEntryCount() - 50, Float.NaN);
                        int index = dataSets.indexOf(set);
                        data.removeEntry(e, index);
                    }
                    data.notifyDataChanged();
                    chart.notifyDataSetChanged();
                    chart.invalidate();
                    tpm++;
                }
            });
        }
        if(type == "humidity") {
            ArrayList<Entry> values = new ArrayList<>();
            LineDataSet set = new LineDataSet(values, "Humidity");
            set.setDrawValues(false);
            set.setDrawCircles(false);
            set.setLineWidth(2.5f);
            set.setColor(Color.BLACK);
            dataSets.add(set);

            model.getHumidity().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    set.addEntry(new Entry(th, Float.parseFloat(s)));
                    if (set.getEntryCount() > 50) {
                        Entry e = set.getEntryForXValue(set.getEntryCount() - 50, Float.NaN);
                        int index = dataSets.indexOf(set);
                        data.removeEntry(e, index);
                    }
                    data.notifyDataChanged();
                    chart.notifyDataSetChanged();
                    chart.invalidate();
                    th++;
                }
            });
        }


    }

}
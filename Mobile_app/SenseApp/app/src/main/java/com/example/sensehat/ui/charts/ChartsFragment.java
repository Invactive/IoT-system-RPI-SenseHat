package com.example.sensehat.ui.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sensehat.databinding.FragmentChartsBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.sql.Date;
import java.text.ParseException;
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


        //Ustawienia wykresu
        LineChart chart = (LineChart) binding.chart;
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
        Legend legend = chart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setWordWrapEnabled(true);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setGranularityEnabled(true);
        //  leftAxis.setAxisMinimum(-30f);
        //  leftAxis.setAxisMaximum(110f);
        //  leftAxis.setYOffset(-9f);
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        LineData data = new LineData(dataSets);
        chart.setData(data);

        mViewModel = new ViewModelProvider(this).get(ChartsViewModel.class);
        updater(mViewModel, "timestamp", chart, data, dataSets);
        updater(mViewModel, "tempC", chart, data, dataSets);
        updater(mViewModel, "tempF", chart, data, dataSets);
//        updater(mViewModel, "pressHpa", chart, data, dataSets);
//        updater(mViewModel, "pressMmhg", chart, data, dataSets);
//        updater(mViewModel, "humidity", chart, data, dataSets);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static long getDateInMilliSeconds(String givenDateString, String format) {
        String DATE_TIME_FORMAT = format;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH);
        long timeInMilliseconds = 1;
        try {
            java.util.Date mDate = sdf.parse(givenDateString);
            timeInMilliseconds = mDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeInMilliseconds;
    }

    public void updater(ChartsViewModel model, String type, LineChart chart,LineData data, ArrayList<ILineDataSet> dataSets){

        if(type == "timestamp"){
            XAxis topAxis = chart.getXAxis();
            topAxis.setGranularity(1f);
            topAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            ArrayList<String> arr = new ArrayList<>();
            topAxis.setLabelCount(arr.size(), false);
//            topAxis.setValueFormatter(new ValueFormatter() {
//                List<String> datesList = arr;
//                @Override
//                public String getFormattedValue(float value) {
//                    Integer position = Math.round(value);
//                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//
//                    java.util.Date date = null;
//                    String output = null;
//
//                    if (value > 1 && value < 2) {
//                        position = 0;
//                    } else if (value > 2 && value < 3) {
//                        position = 1;
//                    } else if (value > 3 && value < 4) {
//                        position = 2;
//                    } else if (value > 4 && value <= 5) {
//                        position = 3;
//                    }
//                    if (position < datesList.size())
//                        return sdf.format(new Date((getDateInMilliSeconds(datesList.get(position), "HH:mm:ss"))));
//                    return "";
//                }
//            });
            model.getTimestamp().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    arr.add(s);
                }
            });


        }

        if(type == "tempC") {

            YAxis leftAxis = chart.getAxisLeft();
            leftAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return value+" °";
                }
            });

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

            YAxis leftAxis = chart.getAxisLeft();
            leftAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return value+" °";
                }
            });

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

            YAxis leftAxis = chart.getAxisLeft();
            leftAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return value+" %";
                }
            });

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
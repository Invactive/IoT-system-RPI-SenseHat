package com.example.sensehat.ui.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

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
import java.util.Timer;
import java.util.TimerTask;

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

        Switch temperatureSwitch = binding.switchTemperature;
        Switch pressureSwitch = binding.switchPressure;
        Switch humiditySwitch = binding.switchHumidity;


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

        temperatureSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    updater(mViewModel, "tempC", chart, data, dataSets);
                    updater(mViewModel, "tempF", chart, data, dataSets);
                } else {
                    ILineDataSet fahren = chart.getData().getDataSetByLabel("Temperature Fahrenheit", true);
                    ILineDataSet celsius = chart.getData().getDataSetByLabel("Temperature Celcius", true);
//                    chart.getData().removeDataSet(fahren);
//                    chart.getData().removeDataSet(celsius);
                    chart.getLineData().removeDataSet(fahren);
                    chart.getLineData().removeDataSet(celsius);
                    ttc = 0;
                    ttf = 0;
                }
            }
        });

        pressureSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    updater(mViewModel, "pressHpa", chart, data, dataSets);
                    updater(mViewModel, "pressMmhg", chart, data, dataSets);
                } else {
                    ILineDataSet hpa = chart.getData().getDataSetByLabel("Pressure Hpa", true);
                    ILineDataSet mmhg = chart.getData().getDataSetByLabel("Pressure Mmhg", true);
                    chart.getData().removeDataSet(hpa);
                    chart.getData().removeDataSet(mmhg);
                    tph = 0;
                    tpm = 0;
                }
            }
        });

        humiditySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    updater(mViewModel, "humidity", chart, data, dataSets);
                } else {
                    ILineDataSet humi = chart.getData().getDataSetByLabel("Humidity", true);
                    chart.getData().removeDataSet(humi);
                    th = 0;
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void updater(ChartsViewModel model, String type, LineChart chart,LineData data, ArrayList<ILineDataSet> dataSets){

//        if(type == "tempC"){
//            XAxis topAxis = chart.getXAxis();
//            topAxis.setGranularity(1f);
//            topAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//            ArrayList<String> arrDates = new ArrayList<>();
//            arrDates.add("2023-01-24 17:39:04.914442");
//            arrDates.add("2023-01-24 17:40:04.914442");
//            topAxis.setLabelCount(arrDates.size(), false);
//            model.getTimestamp().observe(getViewLifecycleOwner(), new Observer<String>() {
//                @Override
//                public void onChanged(String s) {
//                    arrDates.add(s);
//                }
//            });
//            topAxis.setValueFormatter(new ValueFormatter() {
//                List<String> datesList = arrDates;
//                @Override
//                public String getFormattedValue(float value) {
//
//                    return datesList.get(ttc);
//                }
//            });
//        }
//
//        if(type == "tempF"){
//            XAxis topAxis = chart.getXAxis();
//            topAxis.setGranularity(1f);
//            topAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//            ArrayList<String> arrDates = new ArrayList<>();
//            arrDates.add("2023-01-24 17:39:04.914442");
//            arrDates.add("2023-01-24 17:40:04.914442");
//            topAxis.setLabelCount(arrDates.size(), false);
//            model.getTimestamp().observe(getViewLifecycleOwner(), new Observer<String>() {
//                @Override
//                public void onChanged(String s) {
//                    arrDates.add(s);
//                }
//            });
//            topAxis.setValueFormatter(new ValueFormatter() {
//                List<String> datesList = arrDates;
//                @Override
//                public String getFormattedValue(float value) {
//
//                    return datesList.get(ttf);
//                }
//            });
//        }
//
//        if(type == "pressHpa"){
//            XAxis topAxis = chart.getXAxis();
//            topAxis.setGranularity(1f);
//            topAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//            ArrayList<String> arrDates = new ArrayList<>();
//            arrDates.add("2023-01-24 17:39:04.914442");
//            arrDates.add("2023-01-24 17:40:04.914442");
//            topAxis.setLabelCount(arrDates.size(), false);
//            model.getTimestamp().observe(getViewLifecycleOwner(), new Observer<String>() {
//                @Override
//                public void onChanged(String s) {
//                    arrDates.add(s);
//                }
//            });
//            topAxis.setValueFormatter(new ValueFormatter() {
//                List<String> datesList = arrDates;
//                @Override
//                public String getFormattedValue(float value) {
//
//                    return datesList.get(tph);
//                }
//            });
//        }
//
//        if(type == "pressMmhg"){
//            XAxis topAxis = chart.getXAxis();
//            topAxis.setGranularity(1f);
//            topAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//            ArrayList<String> arrDates = new ArrayList<>();
//            arrDates.add("2023-01-24 17:39:04.914442");
//            arrDates.add("2023-01-24 17:40:04.914442");
//            topAxis.setLabelCount(arrDates.size(), false);
//            model.getTimestamp().observe(getViewLifecycleOwner(), new Observer<String>() {
//                @Override
//                public void onChanged(String s) {
//                    arrDates.add(s);
//                }
//            });
//            topAxis.setValueFormatter(new ValueFormatter() {
//                List<String> datesList = arrDates;
//                @Override
//                public String getFormattedValue(float value) {
//                    return datesList.get(tpm);
//                }
//            });
//        }
//
//        if(type == "humidity"){
//            XAxis topAxis = chart.getXAxis();
//            topAxis.setGranularity(1f);
//            topAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//            ArrayList<String> arrDates = new ArrayList<>();
//            YAxis minmax = chart.getAxisLeft();
//            arrDates.add("2023-01-24 17:39:04.914442");
//            arrDates.add("2023-01-24 17:40:04.914442");
//            topAxis.setLabelCount(arrDates.size(), false);
//            model.getTimestamp().observe(getViewLifecycleOwner(), new Observer<String>() {
//                @Override
//                public void onChanged(String s) {
//                    arrDates.add(s);
//                }
//            });
//            topAxis.setValueFormatter(new ValueFormatter() {
//                List<String> datesList = arrDates;
//                @Override
//                public String getFormattedValue(float value) {
//
//                    return datesList.get(th+1);
//                }
//            });
//        }

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
                        try{
                            data.removeEntry(e, index);
                        }
                        catch(ArrayIndexOutOfBoundsException gh){
                            System.out.println("dupa");
                        }

                    }
                    data.notifyDataChanged();
                    chart.notifyDataSetChanged();
                    chart.invalidate();
                    ttc++;
                    System.out.println(s);
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
                        try{
                            data.removeEntry(e, index);
                        }
                        catch(ArrayIndexOutOfBoundsException gh){
                            System.out.println("dupa");
                        }
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
                        try{
                            data.removeEntry(e, index);
                        }
                        catch(ArrayIndexOutOfBoundsException gh){
                            System.out.println("dupa");
                        }
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
                        try{
                            data.removeEntry(e, index);
                        }
                        catch(ArrayIndexOutOfBoundsException gh){
                            System.out.println("dupa");
                        }
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
                        try{
                            data.removeEntry(e, index);
                        }
                        catch(ArrayIndexOutOfBoundsException gh){
                            System.out.println("dupa");
                        }
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
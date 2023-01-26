package com.example.sensehat.ui.data;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sensehat.R;
import com.example.sensehat.databinding.FragmentDataBinding;
import com.example.sensehat.ui.Options.OptionViewModel;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.HashMap;

public class DataFragment extends Fragment {

    private FragmentDataBinding binding;
    private int rowCounter;
    TextView textRow;
    Drawable border_left;
    Drawable border_right;
    Drawable border_top_bot;
    Drawable border_top_left_right;
    Drawable border_square;

    HashMap<String, Boolean> choosenValuesHashMap = new HashMap<String, Boolean>() {{
        put("temperature", Boolean.FALSE);
        put("pressure", Boolean.FALSE);
        put("humidity", Boolean.FALSE);
        put("orientation", Boolean.FALSE);
        put("joystick", Boolean.FALSE);
        put("timestamp", Boolean.FALSE);
    }};

    @SuppressLint("UseCompatLoadingForDrawables")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataViewModel DataViewModel =
                new ViewModelProvider(this).get(DataViewModel.class);

        OptionViewModel OptionViewModel =
                new ViewModelProvider(this).get(OptionViewModel.class);

        binding = FragmentDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TableLayout table = binding.table;
        Switch temperatureSwitch = binding.headerTemperature;
        Switch pressureSwitch = binding.headerPressure;
        Switch humiditySwitch = binding.headerHumidity;
        Switch orientSwitch = binding.headerOrientation;
        Switch joystickSwitch = binding.headerJoystick;
        Switch timestampSwitch = binding.headerTimestamp;

        border_top_bot = getResources().getDrawable(R.drawable.top_bot_shape);
        border_right = getResources().getDrawable(R.drawable.right_shape);
        border_left = getResources().getDrawable(R.drawable.left_shape);
        border_square = getResources().getDrawable(R.drawable.square_shape);
        border_top_left_right = getResources().getDrawable(R.drawable.top_left_right_shape);

        textRow = new TextView(getActivity());
        rowCounter = 0;

        DataViewModel.getDataLogs().observe(getViewLifecycleOwner(), mDataLogs -> {
            System.out.println(mDataLogs);
            drawTable(table, getActivity(), mDataLogs, choosenValuesHashMap);
        });

        OptionViewModel.getTableInterv().observe(getViewLifecycleOwner(), mTableinterv -> {
            DataViewModel.destroyHandler();
            DataViewModel.updateData(mTableinterv);
        });

        OptionViewModel.getServerIP().observe(getViewLifecycleOwner(), mServerIP -> {
            DataViewModel.setServerIP(mServerIP);
        });

        temperatureSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    choosenValuesHashMap.put("temperature", Boolean.TRUE);
                } else {
                    choosenValuesHashMap.put("temperature", Boolean.FALSE);
                }
                System.out.println(rowCounter + "From temp");
                rowCounter = 0;
                table.removeAllViews();
                addTableHeaders(table, getActivity(), choosenValuesHashMap);
            }
        });

        pressureSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    choosenValuesHashMap.put("pressure", Boolean.TRUE);
                } else {
                    choosenValuesHashMap.put("pressure", Boolean.FALSE);
                }
                rowCounter = 0;
                table.removeAllViews();
                addTableHeaders(table, getActivity(), choosenValuesHashMap);
            }
        });

        humiditySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    choosenValuesHashMap.put("humidity", Boolean.TRUE);
                } else {
                    choosenValuesHashMap.put("humidity", Boolean.FALSE);
                }
                rowCounter = 0;
                table.removeAllViews();
                addTableHeaders(table, getActivity(), choosenValuesHashMap);
            }
        });

        orientSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    choosenValuesHashMap.put("orientation", Boolean.TRUE);
                } else {
                    choosenValuesHashMap.put("orientation", Boolean.FALSE);
                    DataViewModel.destroyHandler();
                }
                rowCounter = 0;
                table.removeAllViews();
                addTableHeaders(table, getActivity(), choosenValuesHashMap);
            }
        });

        joystickSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    choosenValuesHashMap.put("joystick", Boolean.TRUE);
                } else {
                    choosenValuesHashMap.put("joystick", Boolean.FALSE);
                }
                rowCounter = 0;
                table.removeAllViews();
                addTableHeaders(table, getActivity(), choosenValuesHashMap);
            }
        });

        timestampSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    choosenValuesHashMap.put("timestamp", Boolean.TRUE);
                } else {
                    choosenValuesHashMap.put("timestamp", Boolean.FALSE);
                }
                rowCounter = 0;
                table.removeAllViews();
                addTableHeaders(table, getActivity(), choosenValuesHashMap);
            }
        });

        return root;
    }

    public void addTableHeaders(TableLayout table, Context context, HashMap<String, Boolean> headers){
        TableRow tabRow = new TableRow(context);

        if(headers.get("temperature") == Boolean.TRUE) {
            TextView headerTemperature = new TextView(getActivity());
            headerTemperature.setText("Temperature [" + "\u00B0C]");
            headerTemperature.setTypeface(null, Typeface.BOLD);
            headerTemperature.setGravity(Gravity.CENTER);
            headerTemperature.setTextColor(Color.BLACK);
            headerTemperature.setBackground(border_square);
            headerTemperature.setPadding(5, 5, 5, 5);
            tabRow.addView(headerTemperature);
        }
        if(headers.get("pressure") == Boolean.TRUE) {
            TextView headerPressure = new TextView(getActivity());
            headerPressure.setText("Pressure [hPa]");
            headerPressure.setTypeface(null, Typeface.BOLD);
            headerPressure.setGravity(Gravity.CENTER);
            headerPressure.setPadding(5, 5, 5, 5);
            headerPressure.setTextColor(Color.BLACK);
            headerPressure.setBackground(border_square);
            tabRow.addView(headerPressure);
        }
        if(headers.get("humidity") == Boolean.TRUE) {
            TextView headerHumidity = new TextView(getActivity());
            headerHumidity.setText("Humidity [%]");
            headerHumidity.setTypeface(null, Typeface.BOLD);
            headerHumidity.setGravity(Gravity.CENTER);
            headerHumidity.setTextColor(Color.BLACK);
            headerHumidity.setPadding(5, 5, 5, 5);
            headerHumidity.setBackground(border_square);
            tabRow.addView(headerHumidity);
        }
        if(headers.get("orientation") == Boolean.TRUE) {
            TextView headerRoll = new TextView(getActivity());
            headerRoll.setText("Roll [" + "\u00B0]");
            headerRoll.setTypeface(null, Typeface.BOLD);
            headerRoll.setGravity(Gravity.CENTER);
            headerRoll.setPadding(5, 5, 5, 5);
            headerRoll.setTextColor(Color.BLACK);
            headerRoll.setBackground(border_square);
            tabRow.addView(headerRoll);

            TextView headerPitch = new TextView(getActivity());
            headerPitch.setText("Pitch [" + "\u00B0]");
            headerPitch.setTypeface(null, Typeface.BOLD);
            headerPitch.setGravity(Gravity.CENTER);
            headerPitch.setPadding(5, 5, 5, 5);
            headerPitch.setTextColor(Color.BLACK);
            headerPitch.setBackground(border_square);
            tabRow.addView(headerPitch);

            TextView headerYaw = new TextView(getActivity());
            headerYaw.setText("Yaw [" + "\u00B0]");
            headerYaw.setTypeface(null, Typeface.BOLD);
            headerYaw.setGravity(Gravity.CENTER);
            headerYaw.setPadding(5, 5, 5, 5);
            headerYaw.setTextColor(Color.BLACK);
            headerYaw.setBackground(border_square);
            tabRow.addView(headerYaw);
        }
        if(headers.get("joystick") == Boolean.TRUE) {
            TextView headerJoystickX = new TextView(getActivity());
            headerJoystickX.setText("Joystick X");
            headerJoystickX.setTypeface(null, Typeface.BOLD);
            headerJoystickX.setGravity(Gravity.CENTER);
            headerJoystickX.setPadding(5, 5, 5, 5);
            headerJoystickX.setTextColor(Color.BLACK);
            headerJoystickX.setBackground(border_square);
            tabRow.addView(headerJoystickX);

            TextView headerJoystickY = new TextView(getActivity());
            headerJoystickY.setText("Joystick Y");
            headerJoystickY.setTypeface(null, Typeface.BOLD);
            headerJoystickY.setGravity(Gravity.CENTER);
            headerJoystickY.setPadding(5, 5, 5, 5);
            headerJoystickY.setTextColor(Color.BLACK);
            headerJoystickY.setBackground(border_square);
            tabRow.addView(headerJoystickY);

            TextView headerJoystickMid = new TextView(getActivity());
            headerJoystickMid.setText("Joystick Mid");
            headerJoystickMid.setTypeface(null, Typeface.BOLD);
            headerJoystickMid.setGravity(Gravity.CENTER);
            headerJoystickMid.setPadding(5, 5, 5, 5);
            headerJoystickMid.setTextColor(Color.BLACK);
            headerJoystickMid.setBackground(border_square);
            tabRow.addView(headerJoystickMid);
        }
        if(headers.get("timestamp") == Boolean.TRUE) {
            TextView headerTimestamp = new TextView(getActivity());
            headerTimestamp.setText("Timestamp");
            headerTimestamp.setTypeface(null, Typeface.BOLD);
            headerTimestamp.setGravity(Gravity.CENTER);
            headerTimestamp.setPadding(5, 5, 5, 5);
            headerTimestamp.setBackground(border_square);
            headerTimestamp.setTextColor(Color.BLACK);
            tabRow.addView(headerTimestamp);
        }

        table.addView(tabRow);

    }

    public void drawTable(TableLayout table, Context context, HashMap<String, ArrayList<Object>> data, HashMap<String, Boolean> headers){
        TableRow recordRow = new TableRow(context);
        recordRow.setId(rowCounter);
        recordRow.setPadding(0, 0, 0, 0);
        System.out.println("ROW COUNTER " + rowCounter);

        try {
            if (rowCounter >= 10) {
                table.removeViewAt(1);
            }
        }catch(Exception ignored){

        }

        if(headers.get("temperature") == Boolean.TRUE) {
            TextView tvTemp = new TextView(context);
            tvTemp.setText(data.get("temperature").get(rowCounter).toString());
            tvTemp.setPadding(30, 30, 30, 30);
            tvTemp.setBackground(border_square);
            tvTemp.setTextColor(Color.BLACK);
            tvTemp.setGravity(Gravity.CENTER);
            recordRow.addView(tvTemp);
        }
        if(headers.get("pressure") == Boolean.TRUE) {
            TextView tvPress = new TextView(context);
            tvPress.setText(data.get("pressure").get(rowCounter).toString());
            tvPress.setPadding(30, 30, 30, 30);
            tvPress.setBackground(border_square);
            tvPress.setTextColor(Color.BLACK);
            tvPress.setGravity(Gravity.CENTER);
            recordRow.addView(tvPress);
        }
        if(headers.get("humidity") == Boolean.TRUE) {
            TextView tvHumi = new TextView(context);
            tvHumi.setText(data.get("humidity").get(rowCounter).toString());
            tvHumi.setPadding(30, 30, 30, 30);
            tvHumi.setBackground(border_square);
            tvHumi.setTextColor(Color.BLACK);
            tvHumi.setGravity(Gravity.CENTER);
            recordRow.addView(tvHumi);
        }
        if(headers.get("orientation") == Boolean.TRUE) {
            TextView tvRoll = new TextView(context);
            tvRoll.setText(data.get("roll").get(rowCounter).toString());
            tvRoll.setPadding(30, 30, 30, 30);
            tvRoll.setBackground(border_square);
            tvRoll.setTextColor(Color.BLACK);
            tvRoll.setGravity(Gravity.CENTER);
            recordRow.addView(tvRoll);

            TextView tvPitch = new TextView(context);
            tvPitch.setText(data.get("pitch").get(rowCounter).toString());
            tvPitch.setPadding(30, 30, 30, 30);
            tvPitch.setBackground(border_square);
            tvPitch.setTextColor(Color.BLACK);
            tvPitch.setGravity(Gravity.CENTER);
            recordRow.addView(tvPitch);

            TextView tvYaw = new TextView(context);
            tvYaw.setText(data.get("yaw").get(rowCounter).toString());
            tvYaw.setPadding(30, 30, 30, 30);
            tvYaw.setBackground(border_square);
            tvYaw.setTextColor(Color.BLACK);
            tvYaw.setGravity(Gravity.CENTER);
            recordRow.addView(tvYaw);
        }
        if(headers.get("joystick") == Boolean.TRUE) {
            TextView tvJoyX = new TextView(context);
            tvJoyX.setText(data.get("joyX").get(rowCounter).toString());
            tvJoyX.setPadding(30, 30, 30, 30);
            tvJoyX.setBackground(border_square);
            tvJoyX.setTextColor(Color.BLACK);
            tvJoyX.setGravity(Gravity.CENTER);
            recordRow.addView(tvJoyX);

            TextView tvJoyY = new TextView(context);
            tvJoyY.setText(data.get("joyY").get(rowCounter).toString());
            tvJoyY.setPadding(30, 30, 30, 30);
            tvJoyY.setBackground(border_square);
            tvJoyY.setTextColor(Color.BLACK);
            tvJoyY.setGravity(Gravity.CENTER);
            recordRow.addView(tvJoyY);

            TextView tvJoyMid = new TextView(context);
            tvJoyMid.setText(data.get("joyMid").get(rowCounter).toString());
            tvJoyMid.setPadding(30, 30, 30, 30);
            tvJoyMid.setBackground(border_square);
            tvJoyMid.setTextColor(Color.BLACK);
            tvJoyMid.setGravity(Gravity.CENTER);
            recordRow.addView(tvJoyMid);
        }
        if(headers.get("timestamp") == Boolean.TRUE) {
            TextView tvTime = new TextView(context);
            tvTime.setText(data.get("timestamp").get(rowCounter).toString());
            tvTime.setPadding(30, 30, 30, 30);
            tvTime.setBackground(border_square);
            tvTime.setTextColor(Color.BLACK);
            tvTime.setGravity(Gravity.CENTER);
            recordRow.addView(tvTime);
        }

        rowCounter++;

        table.addView(recordRow);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}




package com.example.sensehat.ui.data;

import androidx.lifecycle.ViewModelProvider;

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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sensehat.R;
import com.example.sensehat.databinding.FragmentDataBinding;

public class DataFragment extends Fragment {

    private FragmentDataBinding binding;
    private int rowCounter;
    TextView textRow;
    Drawable border_left;
    Drawable border_right;
    Drawable border_top_bot;
    Drawable border_square;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataViewModel DataViewModel =
                new ViewModelProvider(this).get(DataViewModel.class);

        binding = FragmentDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        TableLayout table = binding.table;
        border_top_bot = getResources().getDrawable(R.drawable.top_bot_shape);
        border_right = getResources().getDrawable(R.drawable.right_shape);
        border_left = getResources().getDrawable(R.drawable.left_shape);
        border_square = getResources().getDrawable(R.drawable.square_shape);

        textRow = new TextView(getActivity());
        rowCounter = 0;


        DataViewModel.updateTable(1L);
//        DataViewModel.getRow().observe(getViewLifecycleOwner(), textRow::setText);


        DataViewModel.getChoosenValues().observe(getViewLifecycleOwner(), mChoosenValues -> {
            addTableHeaders(table, getActivity());
//            if(textRow.getParent() != null) {
//                System.out.println((ViewGroup)textRow.getParent());
//                ((ViewGroup)textRow.getParent()).removeView(textRow); // <- fix
//            }
//            System.out.println(mChoosenValues.values());

//            drawTable(table, getActivity());

        });

        DataViewModel.getDataLogs().observe(getViewLifecycleOwner(), mDataLogs -> {
//            if(textRow.getParent() != null) {
//                System.out.println((ViewGroup)textRow.getParent());
//                ((ViewGroup)textRow.getParent()).removeView(textRow); // <- fix
//            }
//            System.out.println(mChoosenValues.values());

            drawTable(table, getActivity());

        });
//        drawTable(table, getActivity());

//        System.out.println(hm.get(hm));

//        table.removeAllViews();

        return root;
    }

    public void addTableHeaders(TableLayout table, Context context){
        TableRow tabRow = new TableRow(context);

        TextView headerTemperature = new TextView(getActivity());
        headerTemperature.setText("Temperature [" + "\u00B0C]");
        headerTemperature.setTypeface(null, Typeface.BOLD);
        headerTemperature.setGravity(Gravity.CENTER);
        headerTemperature.setTextColor(Color.BLACK);
        headerTemperature.setBackground(border_square);
        headerTemperature.setPadding(5, 5, 5, 5);
        tabRow.addView(headerTemperature);

        TextView headerPressure = new TextView(getActivity());
        headerPressure.setText("Pressure [hPa]");
        headerPressure.setTypeface(null, Typeface.BOLD);
        headerPressure.setGravity(Gravity.CENTER);
        headerPressure.setPadding(5, 5, 5, 5);
        headerPressure.setTextColor(Color.BLACK);
        headerPressure.setBackground(border_square);
        tabRow.addView(headerPressure);

        TextView headerHumidity = new TextView(getActivity());
        headerHumidity.setText("Humidity [%]");
        headerHumidity.setTypeface(null, Typeface.BOLD);
        headerHumidity.setGravity(Gravity.CENTER);
        headerHumidity.setTextColor(Color.BLACK);
        headerHumidity.setPadding(5, 5, 5, 5);
        headerHumidity.setBackground(border_square);
        tabRow.addView(headerHumidity);

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
        headerJoystickX.setBackground(border_square);
        tabRow.addView(headerJoystickY);

        TextView headerJoystickMid = new TextView(getActivity());
        headerJoystickMid.setText("Joystick Mid");
        headerJoystickMid.setTypeface(null, Typeface.BOLD);
        headerJoystickMid.setGravity(Gravity.CENTER);
        headerJoystickMid.setPadding(5, 5, 5, 5);
        headerJoystickMid.setTextColor(Color.BLACK);
        headerJoystickMid.setBackground(border_square);
        tabRow.addView(headerJoystickMid);

        TextView headerTimestamp = new TextView(getActivity());
        headerTimestamp.setText("Timestamp");
        headerTimestamp.setTypeface(null, Typeface.BOLD);
        headerTimestamp.setGravity(Gravity.CENTER);
        headerTimestamp.setPadding(5, 5, 5, 5);
        headerTimestamp.setBackground(border_square);
        headerTimestamp.setTextColor(Color.BLACK);
        tabRow.addView(headerTimestamp);

        table.addView(tabRow);

    }

    public void drawTable(TableLayout table, Context context){
        TableRow recordRow = new TableRow(context);
        recordRow.setId(rowCounter);
        recordRow.setPadding(0, 0, 0, 0);

        if(rowCounter >= 10){
            table.removeViewAt(1);
        }


        TextView t1v = new TextView(context);

        t1v.setText("Test1");
        t1v.setPadding(30, 30, 30, 30);
        t1v.setTextColor(Color.BLACK);
        t1v.setGravity(Gravity.CENTER);
        recordRow.addView(t1v);


        TextView t2v = new TextView(context);
        t2v.setText(Integer.toString(rowCounter));
        t2v.setPadding(30, 30, 30, 30);
        t2v.setTextColor(Color.BLACK);
        t2v.setGravity(Gravity.CENTER);
        recordRow.addView(t2v);

        TextView t3v = new TextView(context);
        t3v.setText(Integer.toString(rowCounter));
        t3v.setPadding(30, 30, 30, 30);
        t3v.setTextColor(Color.BLACK);
        t3v.setGravity(Gravity.CENTER);
        recordRow.addView(t3v);
//
//        TextView t4v = new TextView(context);
//        t4v.setText("Test4");
//        t4v.setPadding(30, 30, 30, 30);
//        t4v.setTextColor(Color.BLACK);
//        t4v.setGravity(Gravity.CENTER);
//        recordRow.addView(t4v);

        table.addView(recordRow);

        rowCounter ++;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



//
//    arrTemp.set(i, mRepo.getLogsData().getValue().get("temperature").getJSONObject(i).getJSONObject("temp").getDouble("tempC"));
//                            arrTemp.set(i, mRepo.getLogsData().getValue().get("humidity").getJSONObject(i).getDouble("humidity"));
//                            arrHumi.set(i, mRepo.getLogsData().getValue().get("humidity").getJSONObject(i).getDouble("humidity"));
//                            arrPress.set(i, mRepo.getLogsData().getValue().get("pressure").getJSONObject(i).getDouble("press_hpa"));
//                            arrRoll.set(i, mRepo.getLogsData().getValue().get("accelerometer").getJSONObject(i).getDouble("roll"));
//                            arrPitch.set(i, mRepo.getLogsData().getValue().get("accelerometer").getJSONObject(i).getDouble("pitch"));
//                            arrYaw.set(i, mRepo.getLogsData().getValue().get("accelerometer").getJSONObject(i).getDouble("yaw"));
//                            arrJoyX.set(i, mRepo.getLogsData().getValue().get("joystick").getJSONObject(i).getDouble("X"));
//                            arrJoyY.set(i, mRepo.getLogsData().getValue().get("joystick").getJSONObject(i).getDouble("Y"));
//                            arrJoyMid.set(i, mRepo.getLogsData().getValue().get("joystick").getJSONObject(i).getDouble("Mid"));
}




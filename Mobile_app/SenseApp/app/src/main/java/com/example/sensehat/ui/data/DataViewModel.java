package com.example.sensehat.ui.data;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.sensehat.data.RepositoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private RepositoryModel mRepo;
    private Handler mHandler;
    private int rowCounter;

    public DataViewModel() {
        mRepo = new RepositoryModel();
        mText = new MutableLiveData<>();
        mHandler = new Handler();
        mRepo.setIP("25.78.72.7");
        rowCounter = 0;
        // GET DATA ONCE FOR THE FIRST TIME HERE

//        EXAMPLE: get value of temperature from temperature sensor in degrees mRepo.getTemperatureDataChart().getValue().get("tempCTemp").toString()
//        mText.setValue(mRepo.getTemperatureDataChart().getValue().toString());
        updateTable(2L);
//        EXAMPLE: get value of pressure in hpa mRepo.getPressureDataChart().getValue().get("pressHpa").toString()
//        mText.setValue(mRepo.getPressureDataChart().getValue().toString());

//        EXAMPLE: get value of humidity mRepo.getHumidityDataChart().getValue().get("humi").toString()
//        mText.setValue(mRepo.getHumidityDataChart().getValue().toString());

//        EXAMPLE: get value of degrees in roll axis mRepo.getOrientationDataChart().getValue().get("roll").toString()
//        mText.setValue(mRepo.getAccelerometerDataChart().getValue().toString());

//        EXAMPLE: get value of degrees in roll axis mRepo.getOrientationDataChart().getValue().get("roll_deg").toString()
//        mText.setValue(mRepo.getOrientationDataChart().getValue().toString());

//        EXAMPLE: get value of compass mRepo.getCompassData().getValue().toString()
//        mText.setValue(mRepo.getCompassData().getValue().toString());

//        EXAMPLE: get X value of joystick mRepo.getJoystickData().getValue().get("X").toString()
//        mText.setValue(mRepo.getJoystickData().getValue().toString());

//        EXAMPLE: get led red color of led 43 mRepo.getLedsData().getValue().get("43").get(0).toString()
//        mText.setValue(mRepo.getLedsData().getValue().toString());

//        EXAMPLE: get first JSONObject of joystick mRepo.getLogsData().getValue().get("joystick").getJSONObject(0);
//        try {
//            mText.setValue(mRepo.getLogsData().getValue().get("joystick").getJSONObject(0).toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        EXAMPLE: get value of X in first JSONObject joystick mRepo.getLogsData().getValue().get("joystick").getJSONObject(0).getInt("X");
//        try {
//            mText.setValue(String.valueOf(mRepo.getLogsData().getValue().get("joystick").getJSONObject(0).getInt("X")));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

    public LiveData<String> getText() { return mText; }

    public void addtable(TableLayout table, Context context){
        System.out.println("Table added");
        TableRow tabRow = new TableRow(context);
        TextView header1 = new TextView(context);
        header1.setText("Timestamp");
        header1.setGravity(Gravity.CENTER);
        header1.setPadding(30, 30, 30, 30);
        header1.setTextColor(Color.BLACK);
        tabRow.addView(header1);

        TextView header2 = new TextView(context);
        header2.setText("Temperature");
        header2.setGravity(Gravity.CENTER);
        header2.setPadding(30, 30, 30, 30);
        header2.setTextColor(Color.BLACK);
        tabRow.addView(header2);

        TextView header3 = new TextView(context);
        header3.setText("Humidity");
        header3.setGravity(Gravity.CENTER);
        header3.setTextColor(Color.BLACK);
        header3.setPadding(30, 30, 30, 30);
        tabRow.addView(header3);

        TextView header4 = new TextView(context);
        header4.setText("Joystick");
        header4.setGravity(Gravity.CENTER);
        header4.setPadding(30, 30, 30, 30);
        header4.setTextColor(Color.BLACK);
        tabRow.addView(header4);

        table.addView(tabRow);
    }


    public void updateTable(Long delay){
        mHandler.postDelayed(new Runnable(){
            public void run(){
                // REQUESTS
//                System.out.println(mRepo.getLogsData().getValue().get("joystick"));
//                System.out.println(mRepo.getLogsData().getValue().get("temperature"));
//                System.out.println(mRepo.getLogsData().getValue().get("pressure"));
//                System.out.println(mRepo.getLogsData().getValue().get("humidity"));
//                System.out.println(mRepo.getLogsData().getValue().get("compass"));
//                System.out.println(mRepo.getLogsData().getValue().get("accelerometer"));
//                System.out.println(mRepo.getLogsData().getValue().get("orientation"));
//                System.out.println(mRepo.getLogsData().getValue().get("pixels"));

                rowCounter += 1;
                System.out.println("ROWCOUNTER: " + rowCounter);

                // WORKING!!!
//                mRepo.putLedsRequest(0,0,3,4,255);
//                mRepo.putIntervalRequest(i);
//                mRepo.putResetLedsRequest();
                mHandler.postDelayed(this, delay * 1000);
            }
        }, delay * 1000);
    }

    public void drawTable(TableLayout table, Context context){
        System.out.println("Table draw");
        TableRow recordRow = new TableRow(context);
        recordRow.setId(rowCounter);

        TextView t1v = new TextView(context);

        t1v.setText("Test1");
        t1v.setPadding(30, 30, 30, 30);
        t1v.setTextColor(Color.WHITE);
        t1v.setGravity(Gravity.CENTER);
        recordRow.addView(t1v);

        TextView t2v = new TextView(context);
        t2v.setText("Test2");
        t2v.setPadding(30, 30, 30, 30);
        t2v.setTextColor(Color.WHITE);
        t2v.setGravity(Gravity.CENTER);
        recordRow.addView(t2v);

        TextView t3v = new TextView(context);
        t3v.setText("Test3");
        t3v.setPadding(30, 30, 30, 30);
        t3v.setTextColor(Color.WHITE);
        t3v.setGravity(Gravity.CENTER);
        recordRow.addView(t3v);

        TextView t4v = new TextView(context);
        t4v.setText("Test4");
        t4v.setPadding(30, 30, 30, 30);
        t4v.setTextColor(Color.WHITE);
        t4v.setGravity(Gravity.CENTER);
        recordRow.addView(t4v);

        table.addView(recordRow);
    }


}
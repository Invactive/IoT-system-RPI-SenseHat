package com.example.sensehat.ui.data;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataViewModel extends ViewModel {

    private final MutableLiveData<String> mRow;
    private RepositoryModel mRepo;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private int rowCounter;

//    Data prepared for table
    private HashMap<String, Boolean> choosenValuesHashMap;
    private final MutableLiveData<HashMap<String, Boolean>> mChoosenValues;

    private MutableLiveData<HashMap<String, JSONArray>> mDataLogs;



    public DataViewModel() {
        mRepo = new RepositoryModel();
        mRow = new MutableLiveData<>();
        mHandler = new Handler();

        mRepo.setIP("25.78.72.7");

        choosenValuesHashMap = new HashMap<>();
        mChoosenValues = new MutableLiveData<>();

        mDataLogs = new MutableLiveData<>();

        choosenValuesHashMap.put("Temperature", Boolean.FALSE);
        mChoosenValues.setValue(mChoosenValues.getValue());


        Thread thread = new Thread(){
            @Override
            public void run(){
                updateTable(100L);
            }
        };
        thread.start();



    }



//    public LiveData<String> getText() { return mText; }

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

//    public LiveData<String> getRow() { return mRow; }
    public LiveData<HashMap<String, Boolean>> getChoosenValues() { return mChoosenValues; }
    public LiveData<HashMap<String, JSONArray>> getDataLogs() { return mDataLogs; }



    public void updateTable(Long delay){
        mHandler.postDelayed(new Runnable(){
            public void run(){
                // REQUESTS
//                System.out.println(mChoosenValues.getValue().get("Temperature").toString());
//                System.out.println("ROWCOUNTER 10");
                choosenValuesHashMap.put("Temperature", Boolean.TRUE);
                choosenValuesHashMap.put("Pressure", Boolean.TRUE);
                choosenValuesHashMap.put("Humidity", Boolean.FALSE);
                mChoosenValues.setValue(mChoosenValues.getValue());


                System.out.println("Timer");


//                mChoosenValues.setValue(choosenValuesHashMap);
//                mRow.setValue(mRepo.getLogsData().getValue().get("temperature").toString());
//                mRow.setValue(mRepo.getLogsData().getValue().get("joystick").toString());
//                System.out.println(mRepo.getLogsData().getValue().get("joystick"));
//                System.out.println(mRepo.getLogsData().getValue().get("temperature"));
//                System.out.println(mRepo.getLogsData().getValue().get("pressure"));
//                System.out.println(mRepo.getLogsData().getValue().get("humidity"));
//                System.out.println(mRepo.getLogsData().getValue().get("compass"));
//                System.out.println(mRepo.getLogsData().getValue().get("accelerometer"));
//                System.out.println(mRepo.getLogsData().getValue().get("orientation"));
//                System.out.println(mRepo.getLogsData().getValue().get("pixels"));

//                System.out.println("ROWCOUNTER: " + rowCounter);
                rowCounter++;
                // WORKING!!!
//                mRepo.putLedsRequest(0,0,3,4,255);
//                mRepo.putIntervalRequest(i);
//                mRepo.putResetLedsRequest();
                mHandler.postDelayed(this, delay*2000);
            }
        }, delay*2000);
    }


}
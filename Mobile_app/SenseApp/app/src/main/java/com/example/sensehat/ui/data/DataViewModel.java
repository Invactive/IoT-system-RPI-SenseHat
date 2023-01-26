package com.example.sensehat.ui.data;

import android.annotation.SuppressLint;
import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.sensehat.data.RepositoryModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class DataViewModel extends ViewModel {

    private final RepositoryModel mRepo;
    private final Handler mHandler;

//    Data prepared for table
    private final HashMap<String, Boolean> choosenValuesHashMap;
    private final MutableLiveData<HashMap<String, Boolean>> mChoosenHeaders;

    private HashMap<String, JSONArray> inputHashMap;
    private final MutableLiveData<HashMap<String, ArrayList<Object>>> mDataLogs;
    private final ArrayList<Object> timeArr;
    private final ArrayList<Object> tempArr;
    private final ArrayList<Object> pressArr;
    private final ArrayList<Object> humiArr;
    private final ArrayList<Object> rollArr;
    private final ArrayList<Object> pitchArr;
    private final ArrayList<Object> yawArr;
    private final ArrayList<Object> joyXArr;
    private final ArrayList<Object> joyYArr;
    private final ArrayList<Object> joyMidArr;
    private final HashMap<String, ArrayList<Object>> prepHashMap;

    public DataViewModel() {
        mRepo = new RepositoryModel();
        MutableLiveData<String> mRow = new MutableLiveData<>();
        mHandler = new Handler();

        mRepo.setIP("25.78.72.7");

        inputHashMap = new HashMap<>();
        timeArr = new ArrayList<>();
        tempArr = new ArrayList<>();
        pressArr = new ArrayList<>();
        humiArr = new ArrayList<>();
        rollArr = new ArrayList<>();
        pitchArr = new ArrayList<>();
        yawArr = new ArrayList<>();
        joyXArr = new ArrayList<>();
        joyYArr = new ArrayList<>();
        joyMidArr = new ArrayList<>();
        prepHashMap = new HashMap<>();
        mDataLogs = new MutableLiveData<>();

        choosenValuesHashMap = new HashMap<String, Boolean>() {{
            put("temperature", Boolean.TRUE);
            put("pressure", Boolean.FALSE);
            put("humidity", Boolean.FALSE);
            put("roll", Boolean.FALSE);
            put("pitch", Boolean.FALSE);
            put("yaw", Boolean.FALSE);
            put("joystickX", Boolean.FALSE);
            put("joystickY", Boolean.FALSE);
            put("joystickMid", Boolean.FALSE);
            put("timestamp", Boolean.TRUE);
        }};
        mChoosenHeaders = new MutableLiveData<>();
        mChoosenHeaders.setValue(mChoosenHeaders.getValue());

        Thread thread = new Thread(){
            @Override
            public void run(){
                updateData(100L);
            }
        };
        thread.start();

    }

    public LiveData<HashMap<String, Boolean>> getChoosenHeaders() { return mChoosenHeaders; }
    public LiveData<HashMap<String, ArrayList<Object>>> getDataLogs() { return mDataLogs; }

    public void updateData(Long delay){
        mHandler.postDelayed(new Runnable(){
            @SuppressLint("DefaultLocale")
            public void run(){
                // REQUEST
                inputHashMap = mRepo.fetchLogsData().getValue();
                try {
                    for(int i=0; i<10; i++){
                        timeArr.add(inputHashMap.get("timestamp").get(i));
                        tempArr.add(String.format("%.2f", inputHashMap.get("temperature").getJSONObject(i).getJSONObject("temp").getDouble("tempC")));
                        pressArr.add(String.format("%.2f", inputHashMap.get("pressure").getJSONObject(i).getDouble("press_hpa")));
                        humiArr.add(String.format("%.2f",inputHashMap.get("humidity").getJSONObject(i).getDouble("humidity")));
                        rollArr.add(String.format("%.2f",inputHashMap.get("accelerometer").getJSONObject(i).getDouble("roll")));
                        pitchArr.add(String.format("%.2f",inputHashMap.get("accelerometer").getJSONObject(i).getDouble("pitch")));
                        yawArr.add(String.format("%.2f",inputHashMap.get("accelerometer").getJSONObject(i).getDouble("yaw")));
                        joyXArr.add(inputHashMap.get("joystick").getJSONObject(i).getInt("X"));
                        joyYArr.add(inputHashMap.get("joystick").getJSONObject(i).getInt("Y"));
                        joyMidArr.add(inputHashMap.get("joystick").getJSONObject(i).getInt("Mid"));
                    }
                    prepHashMap.put("timestamp", timeArr);
                    prepHashMap.put("temperature", tempArr);
                    prepHashMap.put("pressure", pressArr);
                    prepHashMap.put("humidity", humiArr);
                    prepHashMap.put("roll", rollArr);
                    prepHashMap.put("pitch", pitchArr);
                    prepHashMap.put("yaw", yawArr);
                    prepHashMap.put("joyX", joyXArr);
                    prepHashMap.put("joyY", joyYArr);
                    prepHashMap.put("joyMid", joyMidArr);

                    mDataLogs.setValue(prepHashMap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                choosenValuesHashMap.put("temperature", Boolean.TRUE);
                choosenValuesHashMap.put("pressure", Boolean.TRUE);
                mChoosenHeaders.setValue(mChoosenHeaders.getValue());

                mHandler.postDelayed(this, delay*2000);
            }
        }, delay*2000);
    }



}
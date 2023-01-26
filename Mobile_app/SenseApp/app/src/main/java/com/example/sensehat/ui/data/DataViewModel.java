package com.example.sensehat.ui.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.sensehat.data.RepositoryModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class DataViewModel extends ViewModel {

    private final RepositoryModel mRepo;
    private Handler mHandler;

//    Data prepared for table
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

    private String IP = "25.78.72.7";
    public int interval = 1000;

    public DataViewModel() {
        mRepo = new RepositoryModel();
        mHandler = new Handler();

        mRepo.setIP(IP);

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
        updateData(interval);

    }

    public LiveData<HashMap<String, ArrayList<Object>>> getDataLogs() { return mDataLogs; }

    public void updateData(int delay){
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable(){
            @SuppressLint("DefaultLocale")
            public void run(){
                // REQUEST
                inputHashMap = mRepo.fetchLogsData().getValue();
                try {
                    for(int i=0; i<10; i++){
                        timeArr.add(inputHashMap.get("timestamp").get(i));
                        tempArr.add(String.format("%.2f", Objects.requireNonNull(inputHashMap.get("temperature")).getJSONObject(i).getJSONObject("temp").getDouble("tempC")));
                        pressArr.add(String.format("%.2f", Objects.requireNonNull(inputHashMap.get("pressure")).getJSONObject(i).getDouble("press_hpa")));
                        humiArr.add(String.format("%.2f", Objects.requireNonNull(inputHashMap.get("humidity")).getJSONObject(i).getDouble("humidity")));
                        rollArr.add(String.format("%.2f", Objects.requireNonNull(inputHashMap.get("accelerometer")).getJSONObject(i).getDouble("roll")));
                        pitchArr.add(String.format("%.2f", Objects.requireNonNull(inputHashMap.get("accelerometer")).getJSONObject(i).getDouble("pitch")));
                        yawArr.add(String.format("%.2f", Objects.requireNonNull(inputHashMap.get("accelerometer")).getJSONObject(i).getDouble("yaw")));
                        joyXArr.add(Objects.requireNonNull(inputHashMap.get("joystick")).getJSONObject(i).getInt("X"));
                        joyYArr.add(Objects.requireNonNull(inputHashMap.get("joystick")).getJSONObject(i).getInt("Y"));
                        joyMidArr.add(Objects.requireNonNull(inputHashMap.get("joystick")).getJSONObject(i).getInt("Mid"));
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

                mHandler.postDelayed(this, delay);
            }
        }, delay);
    }

    public void destroyHandler(){
        mHandler.removeCallbacksAndMessages(null);
    }

    public void setServerIP(Context context, String ip){
        IP = ip;
        mRepo.setIP(IP);
        if(ip == "25.78.72.7") {
            Toast.makeText(context, "Connection error",
                    Toast.LENGTH_LONG).show();
        }
    }



}
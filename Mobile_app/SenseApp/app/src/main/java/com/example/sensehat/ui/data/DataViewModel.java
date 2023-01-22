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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataViewModel extends ViewModel {

    private final MutableLiveData<String> mRow;
    private RepositoryModel mRepo;
    private Handler mHandler;
    private int rowCounter;

//    Data prepared for table
//    private final MutableLiveData<String> mTimestamp;
//    private final MutableLiveData<String> mTemperature;
//    private final MutableLiveData<String> mPressure;
//    private final MutableLiveData<String> mHumidity;
//    private final MutableLiveData<String> mRoll;
//    private final MutableLiveData<String> mPitch;
    private HashMap<String, Boolean> choosenValuesHashMap;
    private final MutableLiveData<HashMap<String, Boolean>> mChoosenValues;

    private HashMap<String, Double> values;
    private final MutableLiveData<HashMap<String, Double>> mValues;


    public DataViewModel() {
        mRepo = new RepositoryModel();
        mRow = new MutableLiveData<>();
        mHandler = new Handler();
        mRepo.setIP("25.78.72.7");

        choosenValuesHashMap = new HashMap<>();
        mChoosenValues = new MutableLiveData<>();

        values = new HashMap<>();
        mValues = new MutableLiveData<>();

        choosenValuesHashMap.put("Temperature", Boolean.FALSE);
        mChoosenValues.setValue(mChoosenValues.getValue());
//        mTimestamp = new MutableLiveData<>();
//        mTimestamp = new MutableLiveData<>();

        // GET DATA ONCE FOR THE FIRST TIME HERE

//        EXAMPLE: get value of temperature from temperature sensor in degrees mRepo.getTemperatureDataChart().getValue().get("tempCTemp").toString()
//        mText.setValue(mRepo.getTemperatureDataChart().getValue().toString());
//        updateTable(1L);
        System.out.println("CALLED FROM CONSTRCTOR DVM");
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

//    public LiveData<String> getRow() { return mRow; }
    public LiveData<HashMap<String, Boolean>> getChoosenValues() { return mChoosenValues; }

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

                try {
                    values.put("tempCTemp", mRepo.getLogsData().getValue().get("temperature").getJSONObject(0).getJSONArray("tempCTemp").getDouble(0));
                    System.out.println(values.get("tempCTemp"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


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
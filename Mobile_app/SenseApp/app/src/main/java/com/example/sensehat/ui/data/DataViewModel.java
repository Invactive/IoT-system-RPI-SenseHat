package com.example.sensehat.ui.data;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.sensehat.data.RepositoryModel;

public class DataViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> rText;
    private RepositoryModel mRepo;
    private Handler mHandler;

    public DataViewModel() {
        mRepo = new RepositoryModel();
        mText = new MutableLiveData<>();
        rText = new MutableLiveData<>();
        mHandler = new Handler();
        mRepo.setIP("25.78.72.7");

//        EXAMPLE: get value of temperature from temperature sensor in degrees mRepo.getTemperatureDataChart().getValue().get("tempCTemp").toString()
//        mText.setValue(mRepo.getTemperatureDataChart().getValue().toString());
        timer(2L);
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

    }

    public LiveData<String> getText() { return mText; }
    public LiveData<String> getText1() {
        return rText;
    }


    public void timer(Long delay){
        mHandler.postDelayed(new Runnable(){
            public void run(){
                System.out.println("Task");
                mText.setValue(mRepo.getTemperatureDataChart().getValue().toString());
                System.out.println("TEMP:" + mRepo.getTemperatureDataChart().getValue().toString());
                rText.setValue(mRepo.getPressureDataChart().getValue().toString());
                System.out.println("PRESS:" + mRepo.getPressureDataChart().getValue().toString());
                mRepo.putLedsRequest(1,2,3,4,5);
                mHandler.postDelayed(this, delay * 50);
            }
        }, delay * 50);
    }


}
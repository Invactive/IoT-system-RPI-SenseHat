package com.example.sensehat.ui.charts;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sensehat.data.RepositoryModel;

import java.util.ArrayList;

public class ChartsViewModel extends ViewModel {

    private final MutableLiveData<String> mTemperatureC;
    private final MutableLiveData<String> mTemperatureF;
    private final MutableLiveData<String> mPressureHp;
    private final MutableLiveData<String> mPressureMm;
    private final MutableLiveData<String> mHumidity;
    private final MutableLiveData<String> mTimestamp;
    private RepositoryModel mRepo;
    private Handler mHandler;

    public ChartsViewModel() {
        mRepo = new RepositoryModel();
        mTemperatureC = new MutableLiveData<>();
        mTemperatureF = new MutableLiveData<>();
        mPressureHp = new MutableLiveData<>();
        mPressureMm = new MutableLiveData<>();
        mHumidity = new MutableLiveData<>();
        mTimestamp = new MutableLiveData<>();
        mHandler = new Handler();
        Handler mHandler = new Handler();
        mRepo.setIP("25.78.72.7");
        fetcher(1L);
    }

    public LiveData<String> getTempCData() {
        return mTemperatureC;
    }

    public LiveData<String> getTempFData() {
        return mTemperatureF;
    }

    public LiveData<String> getPressHpaData() {
        return mPressureHp;
    }

    public LiveData<String> getPressMmhgData() {
        return mPressureMm;
    }

    public LiveData<String> getHumidity() {
        return mHumidity;
    }

    public LiveData<String> getTimestamp() {
        return mTimestamp;
    }


    public void fetcher(Long delay){
        mHandler.postDelayed(new Runnable(){
            public void run(){
                System.out.println("Task");
                mTemperatureC.setValue(mRepo.getTemperatureDataChart().getValue().get("tempCTemp").toString());
                mTemperatureF.setValue(mRepo.getTemperatureDataChart().getValue().get("tempFTemp").toString());
                mPressureHp.setValue(mRepo.getPressureDataChart().getValue().get("pressHpa").toString());
                mPressureMm.setValue(mRepo.getPressureDataChart().getValue().get("pressMmhg").toString());
                mHumidity.setValue(mRepo.getHumidityDataChart().getValue().get("humi").toString());
                mTimestamp.setValue(mRepo.getTemperatureDataChart().getValue().get("timestamp").toString());
                mHandler.postDelayed(this, delay);
            }
        }, delay);
    }
}
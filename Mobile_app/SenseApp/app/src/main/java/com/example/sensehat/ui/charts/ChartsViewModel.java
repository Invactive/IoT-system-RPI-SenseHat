package com.example.sensehat.ui.charts;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sensehat.data.RepositoryModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChartsViewModel extends ViewModel {

    private final MutableLiveData<String> mTemperatureC;
    private final MutableLiveData<String> mTemperatureF;
    private final MutableLiveData<String> mPressureHp;
    private final MutableLiveData<String> mPressureMm;
    private final MutableLiveData<String> mHumidity;
    private final MutableLiveData<String> mTimestamp;
    private RepositoryModel mRepo;
    private Handler mHandler;
    private int interval = 1000;
    private String IP = "25.78.72.7";

    public ChartsViewModel() {
        mRepo = new RepositoryModel();
        mTemperatureC = new MutableLiveData<>();
        mTemperatureF = new MutableLiveData<>();
        mPressureHp = new MutableLiveData<>();
        mPressureMm = new MutableLiveData<>();
        mHumidity = new MutableLiveData<>();
        mTimestamp = new MutableLiveData<>();
        mHandler = new Handler();
        mRepo.setIP(IP);
        fetcher(interval);
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


    public void fetcher(int delay){
        System.out.println(delay);
        mHandler.postDelayed(new Runnable(){
            public void run(){
                mTemperatureC.setValue(mRepo.getTemperatureDataChart().getValue().get("tempCTemp").toString());
                mTemperatureF.setValue(mRepo.getTemperatureDataChart().getValue().get("tempFTemp").toString());
                mPressureHp.setValue(mRepo.getPressureDataChart().getValue().get("pressHpa").toString());
                mPressureMm.setValue(mRepo.getPressureDataChart().getValue().get("pressMmhg").toString());
                mHumidity.setValue(mRepo.getHumidityDataChart().getValue().get("humi").toString());
                mTimestamp.setValue(mRepo.getTemperatureDataChart().getValue().get("timestamp").toString());
                mTimestamp.setValue(mRepo.getPressureDataChart().getValue().get("timestamp").toString());
                mTimestamp.setValue(mRepo.getHumidityDataChart().getValue().get("timestamp").toString());
                mHandler.postDelayed(this, delay);
            }
        }, delay);
    }

    public void destroyHandler(){
        mHandler.removeCallbacksAndMessages(null);
    }

    public void setChartInterv(int time){
        interval = time;
    }

    public void setServerIP(String ip){
        IP = ip;
    }

}
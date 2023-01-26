package com.example.sensehat.ui.Options;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OptionViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private static MutableLiveData<Integer> mChartInterv;
    private static MutableLiveData<Integer> mTableInterv;
    private static MutableLiveData<String> mServerIP;

    public OptionViewModel() {
        mText = new MutableLiveData<>();

        mChartInterv = new MutableLiveData<>();
        mTableInterv = new MutableLiveData<>();
        mServerIP = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<Integer> getChartInterv() {
        return mChartInterv;
    }
    public LiveData<Integer> getTableInterv() { return mTableInterv; }
    public LiveData<String> getServerIP() { return mServerIP; }

    public static void setChartInterv(int time){
        mChartInterv.setValue(time);
    }

    public static void setTableInterv(int time){
        mTableInterv.setValue(time);
    }

    public static void setServerIP(String ip){
        mServerIP.setValue(ip);
    }
}


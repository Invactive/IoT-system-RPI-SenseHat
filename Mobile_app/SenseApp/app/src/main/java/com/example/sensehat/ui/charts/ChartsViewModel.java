package com.example.sensehat.ui.charts;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sensehat.data.RepositoryModel;

public class ChartsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private RepositoryModel mRepo;
    private Handler mHandler;

    public ChartsViewModel() {
        mRepo = new RepositoryModel();
        mText = new MutableLiveData<>();
        mHandler = new Handler();
        Handler mHandler = new Handler();
        mRepo.setIP("25.78.72.7");
        timer(2L);
//        mText.setValue(mRepo.getTemperatureDataChart().getValue().get("tempFPress").toString());
    }

    public LiveData<String> getData() {
        return mText;
    }

    public void setData(String data) {
        timer(100L);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void timer(Long delay){
        mHandler.postDelayed(new Runnable(){
            public void run(){
                System.out.println("Task");
                mText.setValue(mRepo.getTemperatureDataChart().getValue().get("tempCTemp").toString());
                mHandler.postDelayed(this, delay);
            }
        }, delay);
    }
}
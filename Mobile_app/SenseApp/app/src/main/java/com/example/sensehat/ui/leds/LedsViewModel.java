package com.example.sensehat.ui.leds;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sensehat.data.RepositoryModel;

import java.util.ArrayList;

public class LedsViewModel extends ViewModel {

    MutableLiveData<ArrayList<ArrayList<Integer>>> myArray;
    ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();


    private RepositoryModel mRepo;
    private Handler mHandler;
    private String IP = "25.78.72.7";
    private int interval = 1000;

    public LedsViewModel() {
        mRepo = new RepositoryModel();
        myArray = new MutableLiveData<>();
        mHandler = new Handler();
        mRepo.setIP(IP);

        Thread thread = new Thread(){
            @Override
            public void run(){
                fetcher(interval);
            }
        };
        thread.start();

    }

    public LiveData<ArrayList<ArrayList<Integer>>> getText() {
        return myArray;
    }


    public void setLeds(int x, int y, int r, int g, int b){
        mRepo.putLedsRequest(x, y, r, g, b);
    }

    public void reset(){
        mRepo.putResetLedsRequest();
    }

    public void fetcher(int delay){
        System.out.println(delay);
        mHandler.postDelayed(new Runnable(){
            public void run(){
                myArray.setValue(mRepo.getLedsData().getValue());
                mHandler.postDelayed(this, delay);
            }
        }, delay);
    }
}
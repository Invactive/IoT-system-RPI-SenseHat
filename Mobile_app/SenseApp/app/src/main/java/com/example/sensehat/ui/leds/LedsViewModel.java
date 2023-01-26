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

    public LedsViewModel() {
        mRepo = new RepositoryModel();
        myArray = new MutableLiveData<>();
        mHandler = new Handler();
        mRepo.setIP("25.78.72.7");

        Thread thread = new Thread(){
            @Override
            public void run(){
                fetcher(1);
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
        mHandler.postDelayed(new Runnable(){
            public void run(){
                myArray.setValue(mRepo.getLedsData().getValue());
                mHandler.postDelayed(this, delay);
            }
        }, delay);
    }
}
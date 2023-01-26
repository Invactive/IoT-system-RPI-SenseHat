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
    int x;
    int y;
    int r;
    int g;
    int b;

    private RepositoryModel mRepo;
    private Handler mHandler;

    public LedsViewModel() {
        mRepo = new RepositoryModel();
        myArray = new MutableLiveData<>();
        mHandler = new Handler();
        mRepo.setIP("25.78.72.7");
        fetcher(1);
    }

    public LiveData<ArrayList<ArrayList<Integer>>> getText() {
        return myArray;
    }

    public LiveData<Integer> setX(int argX){
        x = argX;
        return null;
    }

    public LiveData<Integer> setY(int argY){
        y = argY;
        return null;
    }

    public LiveData<Integer> setR(int argR){
        r = argR;
        return null;
    }

    public LiveData<Integer> setG(int argG){
        g = argG;
        return null;
    }

    public LiveData<Integer> setB(int argB){
        b = argB;
        return null;
    }

    public void reset(){
        mRepo.putResetLedsRequest();
    }


    public void fetcher(int delay){
        mHandler.postDelayed(new Runnable(){
            public void run(){
                for(int i=0; i<64; i++){
                    arrayList.add(i, mRepo.getLedsData().getValue().get(i));
                }
                myArray.setValue(arrayList);
                mRepo.putLedsRequest(x, y, r, g, b);
                mHandler.postDelayed(this, delay);
            }
        }, delay);
    }
}
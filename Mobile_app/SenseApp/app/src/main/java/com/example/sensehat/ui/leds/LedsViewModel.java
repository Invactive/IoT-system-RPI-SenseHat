package com.example.sensehat.ui.leds;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sensehat.data.RepositoryModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LedsViewModel extends ViewModel {

    MutableLiveData<ArrayList<String>> myArray = new MutableLiveData<>();
    ArrayList<String> arrayList = new ArrayList<>();
    private RepositoryModel mRepo;
    private Handler mHandler;

    public LedsViewModel() {
        mRepo = new RepositoryModel();
        myArray = new MutableLiveData<>();
        mHandler = new Handler();
        mRepo.setIP("25.78.72.7");
        fetcher(1);
        System.out.println(arrayList);
    }

    public LiveData<ArrayList<String>> getText() {
        return myArray;
    }

    public void fetcher(int delay){
        mHandler.postDelayed(new Runnable(){
            public void run(){
                for(int i=0; i<=64; i++){
                    arrayList.add(mRepo.getLedsData().getValue().get(Integer.toString(i)).toString());
                }
                myArray.setValue(arrayList);
                mHandler.postDelayed(this, delay);
            }
        }, delay);
    }
}
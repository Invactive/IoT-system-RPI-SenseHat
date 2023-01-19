package com.example.sensehat.ui.charts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sensehat.data.RepositoryModel;

public class ChartsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private RepositoryModel mRepo;

    public ChartsViewModel() {
        mRepo = new RepositoryModel();
        mText = new MutableLiveData<>();
        // call like this to get temperature in F from pressure sensor
//        mText.setValue(mRepo.getTemperatureDataChart().getValue().get("tempFPress").toString());
    }

    public LiveData<String> getText() {
        return mText;
    }
}
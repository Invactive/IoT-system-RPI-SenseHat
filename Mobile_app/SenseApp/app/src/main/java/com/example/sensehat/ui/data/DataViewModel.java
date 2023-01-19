package com.example.sensehat.ui.data;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.sensehat.data.Repository;

import org.json.JSONObject;

public class DataViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    private Repository repo;

    public DataViewModel() {
        repo = new Repository();
        mText = new MutableLiveData<>();
        mText.setValue(repo.getTemperature().toString());
    }

    public LiveData<String> getText() {
        return mText;
    }

}
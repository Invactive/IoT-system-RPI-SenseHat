package com.example.sensehat.ui.leds;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LedsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LedsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is leds fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
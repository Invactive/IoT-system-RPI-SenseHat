package com.example.sensehat.ui.Options;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OptionViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public OptionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is option fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
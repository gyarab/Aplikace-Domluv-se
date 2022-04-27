package com.example.rocnikovaprace.ui.PridejSlovicko;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PridejSlovickoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PridejSlovickoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
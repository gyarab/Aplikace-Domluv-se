package com.example.rocnikovaprace.ui.SpravujSlovicka;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpravujSlovickaModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SpravujSlovickaModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.example.rocnikovaprace.ui.Zacni;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ZacniViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ZacniViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
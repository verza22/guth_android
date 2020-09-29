package com.example.guth.Views;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AcercaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AcercaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Acerca de...");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
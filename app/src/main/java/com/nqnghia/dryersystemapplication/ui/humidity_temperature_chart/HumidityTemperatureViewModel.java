package com.nqnghia.dryersystemapplication.ui.humidity_temperature_chart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HumidityTemperatureViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HumidityTemperatureViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is machine info fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
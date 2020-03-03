package com.nqnghia.dryersystemapplication.ui.device_sensor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeviceSensorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DeviceSensorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is devices and sensors fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
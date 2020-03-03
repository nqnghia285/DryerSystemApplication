package com.nqnghia.dryersystemapplication.ui.machine_system;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MachineSystemViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MachineSystemViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is machine system fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
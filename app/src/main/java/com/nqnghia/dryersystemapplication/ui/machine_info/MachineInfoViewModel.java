package com.nqnghia.dryersystemapplication.ui.machine_info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MachineInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MachineInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is machine info fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
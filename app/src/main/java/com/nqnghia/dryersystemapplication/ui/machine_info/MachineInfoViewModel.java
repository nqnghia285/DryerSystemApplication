package com.nqnghia.dryersystemapplication.ui.machine_info;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class MachineInfoViewModel extends ViewModel {

    private MutableLiveData<HashMap<String, String>> Label;
    private MutableLiveData<HashMap<String, String>> EstablishedData;
    private MutableLiveData<HashMap<String, String>> ConstantlyChangingData;
    private MutableLiveData<HashMap<String, String>> ChangedData;

    public MachineInfoViewModel() {
        Label = new MutableLiveData<>();
        EstablishedData = new MutableLiveData<>();
        ConstantlyChangingData = new MutableLiveData<>();
        ChangedData = new MutableLiveData<>();
    }

    public LiveData<HashMap<String, String>> getLabel() {
        return Label;
    }

    public LiveData<HashMap<String, String>> getEstablishedData() {
        return EstablishedData;
    }

    public LiveData<HashMap<String, String>> getConstantlyChangingData() { return ConstantlyChangingData; }

    public LiveData<HashMap<String, String>> getChangedData() { return ChangedData; }

    public void setLabel(HashMap<String, String> label) {
        if (Label != null) {
            Label.setValue(label);
        }
    }

    public void setEstablishedData(HashMap<String, String> establishedData) {
        if (EstablishedData != null) {
            EstablishedData.setValue(establishedData);
        }
    }

    public void setConstantlyChangingData(HashMap<String, String> constantlyChangingData) {
        if (ConstantlyChangingData != null) {
           ConstantlyChangingData.setValue(constantlyChangingData);
        }
    }

    public void setChangedData(HashMap<String, String> changedData) {
        if (ChangedData != null) {
            ChangedData.setValue(changedData);
        }
    }

    private String getLabel(String key) {
        if (Label.getValue() != null) {
            return Label.getValue().get(key);
        } else {
            return null;
        }
    }

    private String getEstablishedData(String key) {
        if (EstablishedData.getValue() != null) {
            return EstablishedData.getValue().get(key);
        } else {
            return null;
        }
    }

    private String getConstantlyChangingData(String key) {
        if (ConstantlyChangingData.getValue() != null) {
            return ConstantlyChangingData.getValue().get(key);
        } else {
            return null;
        }
    }

    private String getChangedData(String key) {
        if (ChangedData.getValue() != null) {
            return ChangedData.getValue().get(key);
        } else {
            return null;
        }
    }

    void onChangeValueOfLabel(String key, View element) {
        if (element instanceof TextView) {
            ((TextView)element).setText(getLabel(key));
        } else if (element instanceof ImageView) {
            ((ImageView) element).setImageResource(Integer.parseInt(getLabel(key)));
        } else if (element instanceof SeekBar) {
            ((SeekBar)element).setProgress(Integer.parseInt(getLabel(key)));
        }
    }

    void onChangeValueOfEstablishedData(String key, View element) {
        if (element instanceof TextView) {
            ((TextView)element).setText(getEstablishedData(key));
        }
    }

    void onChangeValueOfConstantlyChangingData(String key, View element) {
        if (element instanceof TextView) {
            ((TextView)element).setText(getConstantlyChangingData(key));
        }
    }

    void onChangeValueOfChangedData(String key, View element) {
        if (element instanceof TextView) {
            TextView elem = (TextView)element;
            if (elem instanceof Switch) {
                ((Switch) elem).setChecked(Boolean.parseBoolean(getChangedData(key)));
            } else {
                ((TextView)element).setText(getChangedData(key));
            }
        } else if (element instanceof SeekBar) {
            ((SeekBar)element).setProgress(Integer.parseInt(getChangedData(key)));
        }
    }
}
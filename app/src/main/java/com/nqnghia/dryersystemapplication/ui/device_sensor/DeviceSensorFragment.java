package com.nqnghia.dryersystemapplication.ui.device_sensor;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.nqnghia.dryersystemapplication.MainActivity;
import com.nqnghia.dryersystemapplication.R;

public class DeviceSensorFragment extends Fragment {

    private DeviceSensorViewModel deviceSensorViewModel;
    private MainActivity mainActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        deviceSensorViewModel =
                ViewModelProviders.of(this).get(DeviceSensorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_device_sensor, container, false);
        final TextView textView = root.findViewById(R.id.text_device_sensor);
        deviceSensorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
//            mainActivity.getFab().show();
        }
    }
}
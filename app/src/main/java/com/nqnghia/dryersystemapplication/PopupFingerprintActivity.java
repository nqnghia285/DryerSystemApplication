package com.nqnghia.dryersystemapplication;

import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.security.KeyStore;

import javax.crypto.Cipher;

import static androidx.core.content.ContextCompat.startActivities;

public class PopupFingerprintActivity extends BottomSheetDialogFragment {
    private static final String TAG = "PopupFingerprintActivit";
    private OnCancelClickListener mListener;
    private Button cancelButton;

    public interface OnCancelClickListener {
        void onClick(View v);
    }

    public void setOnCancelClickListener(OnCancelClickListener listener) {
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_popup_fingerprint, container, false);

        cancelButton = v.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(v1 -> {
            if (mListener != null) {
                mListener.onClick(v1);
            }
            dismiss();
        });

        return v;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        Log.e(TAG, "onDismiss: ");
        if (cancelButton != null) {
            cancelButton.callOnClick();
        }
        super.onDismiss(dialog);
    }
}

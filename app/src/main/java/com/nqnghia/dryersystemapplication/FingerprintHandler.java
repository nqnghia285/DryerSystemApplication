package com.nqnghia.dryersystemapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.core.content.ContextCompat;

import static androidx.core.content.ContextCompat.startActivities;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;
    private AuthenticationSucceeded authenticationSucceeded;

    public FingerprintHandler(Context context) {

        this.context = context;

    }

    public interface AuthenticationSucceeded {
        void AuthSucceeded(FingerprintManager.AuthenticationResult result);
    }

    public void addHandler(AuthenticationSucceeded succeeded) {
        authenticationSucceeded = succeeded;
    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {

        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

        this.update("There was an Auth Error. " + errString, false);

    }

    @Override
    public void onAuthenticationFailed() {

        this.update("Auth Failed. ", false);

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

        this.update("Error: " + helpString, false);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

        this.update("You can now access the app.", true);
        if (authenticationSucceeded != null) {
            authenticationSucceeded.AuthSucceeded(result);
        }
    }

    private void update(String s, boolean b) {

//        TextView paraLabel = ((Activity) context).findViewById(R.id.paraLabel);
//        ImageView imageView = ((Activity) context).findViewById(R.id.fingerprintImage);

//        paraLabel.setText(s);

        if (b == false) {

//            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else {

//            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
//            imageView.setImageResource(R.mipmap.action_done);

        }

    }
}
package com.nqnghia.dryersystemapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class LoginActivity extends AppCompatActivity implements ServiceConnection {
    private static final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;
    private Button login;
    private ImageView fingerprint;
    private TextView forgotPassword;

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    private KeyStore keyStore;
    private Cipher cipher;
    private String KEY_NAME = "AndroidKey";

    private LocalService.LocalBinder mLocalBinder;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Tao service va lien ket voi class hien tai
        bindService(new Intent(this, LocalService.class), this, Context.BIND_AUTO_CREATE);

        username = findViewById(R.id.username_edit_text);
        password = findViewById(R.id.password_edit_text);
        forgotPassword = findViewById(R.id.forgot_text_view);
        login = findViewById(R.id.login_button);

        login.setOnClickListener(v -> startMainActivity());

        fingerprint = findViewById(R.id.fingerprint_image_view);
        fingerprint.setOnClickListener(v -> {

            if (!keyguardManager.isKeyguardSecure()){
                showToast("Hãy thêm mật khẩu khóa điện thoại trong cài đặt");
            } else if (!fingerprintManager.hasEnrolledFingerprints()){
                showToast("Hãy thêm ít nhất một dấu vân tay vào điện thoại để sử dụng tính năng này");
            } else {
                FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                FingerprintHandler fingerprintHandler = new FingerprintHandler(v.getContext());
                fingerprintHandler.startAuth(fingerprintManager, cryptoObject);
                fingerprintHandler.addHandler(result -> startMainActivity());

                PopupFingerprintActivity fingerprintActivity = new PopupFingerprintActivity();
                fingerprintActivity.setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
                fingerprintActivity.setOnCancelClickListener(v1 -> {
                    fingerprintHandler.stopAuth();
                });
                fingerprintActivity.show(getSupportFragmentManager(), "popupFingerprint");
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if(!fingerprintManager.isHardwareDetected() ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
                fingerprint.setVisibility(View.INVISIBLE);
            }
        } else {
            fingerprint.setVisibility(View.INVISIBLE);
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            keyStore.load(null);
            keyGenerator.init(
                    new KeyGenParameterSpec.Builder(KEY_NAME,
                            KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                            .setUserAuthenticationRequired(true)
                            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                            .build());
            keyGenerator.generateKey();
        } catch (KeyStoreException | IOException | CertificateException
                | NoSuchAlgorithmException | InvalidAlgorithmParameterException
                | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException
                | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    public void showToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));

        TextView textView = layout.findViewById(R.id.toast_text);
        textView.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder instanceof LocalService.LocalBinder) {
            mLocalBinder = (LocalService.LocalBinder) iBinder;
            //TODO
            mLocalBinder.initSocket("192.168.137.128", 3000);
            mLocalBinder.on("server_send_sensorData", args -> {
                JSONObject object = (JSONObject) args[0];
                try {

//                String data = object.getString("noidung");
                    JSONObject data = object.getJSONObject("noidung");
                    String enable = data.getString("status");
//                String enable = object.getString("enable");
                    Log.d("data receive led", enable);
                    if (enable.equals("1")) {
//                        blowerFanSwitch.setChecked(true);
                    } else {
//                        blowerFanSwitch.setChecked(false);
                    }
                    //--------------------------------------------------
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
}


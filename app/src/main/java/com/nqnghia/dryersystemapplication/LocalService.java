package com.nqnghia.dryersystemapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class LocalService extends Service {
    private static final String TAG = "LocalService";
    private Socket mSocket;
    private static ArrayList<String> mEvent = new ArrayList<>();

    private final LocalBinder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {

        public LocalService getService() {
            return LocalService.this;
        }

        public void initSocket(String ip, int port) {
            try {
                mSocket = IO.socket("http://" + ip +":" + port);
                new Thread(() -> mSocket.connect()).start();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        public void on(String event, Emitter.Listener listener) {
            if (mSocket != null) {
                mSocket.on(event, listener);
                mEvent.add(event);
            } else {
                Log.w(TAG, "on: mSocket is null");
            }
        }

        public void off() {
            if (mSocket != null) {
                mSocket.off();
            } else {
                Log.w(TAG, "on: mSocket is null");
            }
        }

        public void off(String event) {
            if (mSocket != null) {
                mSocket.off(event);
                mEvent.remove(event);
            } else {
                Log.w(TAG, "on: mSocket is null");
            }
        }

        public void off(String event, Emitter.Listener listener) {
            if (mSocket != null) {
                mSocket.off(event, listener);
                mEvent.remove(event);
            } else {
                Log.w(TAG, "on: mSocket is null");
            }
        }

        public void emit(String event, Object... args) {
            if (mSocket != null) {
                mSocket.emit(event, args);
            } else {
                Log.w(TAG, "on: mSocket is null");
            }
        }

        public void emit(String event, Object[] args, Ack ack) {
            if (mSocket != null) {
                mSocket.emit(event, args, ack);
            } else {
                Log.w(TAG, "on: mSocket is null");
            }
        }

        public void close() {
            if (mSocket != null) {
                mSocket.off();
                mSocket.close();
            } else {
                Log.w(TAG, "on: mSocket is null");
            }
        }
    }

    @Override
    public void onDestroy() {
        mSocket.off();
        mSocket.close();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}

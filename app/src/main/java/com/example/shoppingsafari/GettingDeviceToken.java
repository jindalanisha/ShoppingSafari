package com.example.shoppingsafari;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.iid.InstanceIdResult;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class GettingDeviceToken extends FirebaseInstanceIdService {

    public void onTokenRefresh() {
        String Token = FirebaseInstanceId.getInstance ().getToken ();
        Log.d("My Token",Token);
    }
}

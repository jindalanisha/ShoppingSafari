package com.example.shoppingsafari;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class MessagingService extends FirebaseMessagingService {

    public static int NOTIFICATION_ID = 1;

    public void onMessageReceived(RemoteMessage remoteMessage){
        showNotification (remoteMessage.getNotification ().getBody ());
    }
    public void showNotification(String message){
        PendingIntent pn= PendingIntent.getActivity (this,0,new Intent (this, MainActivity.class),0);

        Uri soundUri = RingtoneManager.getDefaultUri (RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = new NotificationCompat.Builder (this).setSmallIcon (R.drawable.ic_stat_name).setContentTitle ("ShoppingSafari").setContentText (message).setContentIntent (pn).setAutoCancel (true).setSound (soundUri).build ();
        NotificationManager notificationManager=(NotificationManager)getSystemService (NOTIFICATION_SERVICE);
        if(NOTIFICATION_ID>1073741824){
            NOTIFICATION_ID = 0;
        }
        notificationManager.notify(NOTIFICATION_ID++,notification);

     }

}

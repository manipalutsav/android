package com.creativecodes.maheutsav2019;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by user on 23-06-2017.
 */

public class FirebaseMessage extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent resultIntent = new Intent(getApplicationContext(),MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(),0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_utsav)
                .setContentTitle("Notification")
                .setAutoCancel(true)
                .setContentText(remoteMessage.getNotification().getBody());

        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }
}

package com.gp.mygp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.gp.mygp.Activity.SpalshActivity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sotra on 3/31/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        // Check if message contains a notification payload.
        if (remoteMessage.getData() != null) {
            Log.e("onMessageReceived", "Message Notification data: " + remoteMessage.getData().toString());
            Map<String , String > map = remoteMessage.getData() ;
            sendNotification(map.get("title") , map.get("body"));
        }

       // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            remoteMessage.getNotification();
            Log.e("onMessageReceived", "Message Notification : " + remoteMessage.getNotification().getBody());
        }



    }


    private void sendNotification(String title , String body ) {
        Intent intent = new Intent(getApplicationContext() , SpalshActivity.class) ;

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);


        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap bitmap = BitmapFactory.decodeResource( getResources(), R.mipmap.ic_launcher);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(body)
                .setLargeIcon(bitmap)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setSound(defaultSoundUri)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority( NotificationCompat.PRIORITY_DEFAULT)
                ;


        final Notification notification = notificationBuilder.build();

        long id =  Calendar.getInstance().getTimeInMillis() ;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify((int) id,notification);
//
    }
}

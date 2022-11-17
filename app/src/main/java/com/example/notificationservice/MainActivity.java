package com.example.notificationservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "General";
    private static final int NOTIFICATION_ID = 1;

    private static final int REQUEST_CODE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bitmap Work - To show the custom icon in the notification
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.exam, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmapLargeIcon = bitmapDrawable.getBitmap();

        //Intent Work
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //Clear the top activities from the stack

        //Pending Intent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Styles In Notification -- Big Picture Style
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .bigPicture(bitmapLargeIcon)
                .bigLargeIcon(bitmapLargeIcon)
                .setBigContentTitle("Expended Big Title Context Text -- Big Picture Style")
                .setSummaryText("Image Notification");

        //Styles In Notification -- Inbox Style
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle()
                .addLine("ABC")
                .addLine("ABC")
                .addLine("ABC")
                .addLine("ABC")
                .addLine("ABC")
                .addLine("ABC")
                .addLine("ABC")
                .addLine("ABC")
                .addLine("ABC")
                .addLine("ABC") //only 7 lines will be shown
                .setBigContentTitle("Expended Big Title Context Text -- Inbox Style")
                .setSummaryText("Inbox Style");

        //Notification Work
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(bitmapLargeIcon) //Larger Icon for Purpose of notification -- Here you set the custom icon you want to show
                    .setSmallIcon(R.drawable.notification) //Small Icon for Apps Logo - Here you set your app logo
                    .setChannelId(CHANNEL_ID) //Pub - Sub Model
                    .setAutoCancel(true) //Removable Notification
                    .setContentIntent(pendingIntent) //Setting Intent
                    .setStyle(bigPictureStyle) //Setting Style
                    .setContentText("Content Text") // This is title of the notification
                    .setSubText("This is sub text - new Message")
                    .build();

            //Channel Work -- Pub Sub Model
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "Testing", NotificationManager.IMPORTANCE_HIGH));

        } else {
            //No need of channel in version lower than oreo (8)
            notification = new Notification.Builder(this)
                    .setLargeIcon(bitmapLargeIcon) //Larger Icon for Purpose of notification -- Here you set the custom icon you want to show
                    .setSmallIcon(R.drawable.notification) //Small Icon for Apps Logo - Here you set your app logo
                    .setContentText("Content Text") // This is title of the notification
                    .setStyle(bigPictureStyle) //Setting Style
                    .setSubText("This is sub text - new Message")
                    .setContentIntent(pendingIntent)
                    .build();
        }

        //Trigger the notification
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}

/*
Pending Intent will be required when user clicks the notification
 */

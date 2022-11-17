# Android_Notification_Service
App that trigger notification

This topic is a part of [My Complete Andorid Course](https://github.com/ananddasani/Android_Apps)

# App Highlight
![NotificationServices Code](https://user-images.githubusercontent.com/74413402/202426164-a0c00f81-4b74-4ff1-8182-9f41ef2cfc48.png)
![NotificationServices_1](https://user-images.githubusercontent.com/74413402/202426168-bde83314-3bb7-48d1-9237-5f5bcfb5f28c.png)
![NotificationServices_2](https://user-images.githubusercontent.com/74413402/202426171-802eb18a-26ea-4171-a293-d8d1a1067635.png)
![NotificationServices_3](https://user-images.githubusercontent.com/74413402/202426173-92b4f03a-43b9-4053-9078-b8244d476623.png)

# Code

1. ActivityMain
```
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
```

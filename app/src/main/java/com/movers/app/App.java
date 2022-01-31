package com.movers.app;

import android.accessibilityservice.GestureDescription;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_ID1 ="channelId1";
    public static final String CHANNEL_ID2 = "channelId2";

    @Override
    public void onCreate() {
        super.onCreate();
        CreateNotificationsChannel();
    }

    private void CreateNotificationsChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel1 = new NotificationChannel(CHANNEL_ID1,
                    "Order Confirmation", NotificationManager.IMPORTANCE_HIGH);

            channel1.setDescription("Order is confirmed");

            NotificationChannel channel2 = new NotificationChannel(CHANNEL_ID2,
                    "New Order",NotificationManager.IMPORTANCE_LOW);

            channel2.setDescription("You  a mew Order");


            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }


    }
}

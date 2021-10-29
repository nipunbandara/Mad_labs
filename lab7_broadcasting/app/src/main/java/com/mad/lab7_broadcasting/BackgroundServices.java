package com.mad.lab7_broadcasting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BackgroundServices extends IntentService {

    public BackgroundServices() {super("BackgroundServices");}

    public static void startAction(Context context){
        //creating an intent for service (broadcast)
        Intent intent = new Intent(context, BackgroundServices.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null){
            for (int i = 0; i < 5; i++){

                //creating a intent to send broadcast message to the mainactivity
                Intent localBroadCastIntent = new Intent(MainActivity.BROADCAST_ACTION);
                localBroadCastIntent.putExtra("value", "BroadCast" + (i +1));
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                sendBroadcast(localBroadCastIntent);
            }
        }
    }


}
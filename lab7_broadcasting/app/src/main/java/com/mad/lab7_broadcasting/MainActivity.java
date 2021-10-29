package com.mad.lab7_broadcasting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_Broadcast;
    Button btn_Start;
    Receiver localListener;
    public static final String BROADCAST_ACTION = "Broadcast action 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_Broadcast = findViewById(R.id.tv_Broadcast);
        btn_Start = findViewById(R.id.btn_Start);

    }

    protected void onStart(){
        super.onStart();
        localListener = new Receiver();


        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener, intentFilter);
    }

    protected void onStop(){
        super.onStop();
        this.unregisterReceiver(localListener);
    }

    public void onClick(View view){
        if(view.getId() == R.id.btn_Start){
            BackgroundServices.startAction(this.getApplicationContext());
        }
    }

    //Receiver declaration
    
    public class Receiver extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent){
            String currentText = tv_Broadcast.getText().toString();

            //getting message which is sent from the broadcast class
            String message = intent.getStringExtra("value");
            currentText += "\nReceived " + message;
        }
    }


}




























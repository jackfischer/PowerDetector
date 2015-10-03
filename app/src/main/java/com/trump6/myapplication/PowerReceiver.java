package com.trump6.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;


public class PowerReceiver extends BroadcastReceiver {
    public PowerReceiver() {
        System.out.println("Inside the power receiver constructor");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        System.out.println("received EVENT!!");

        String action = intent.getAction();

        if (action.equals(Intent.ACTION_POWER_CONNECTED)) {
            // Do something when power connected
            System.out.println("power CONNECTED");
        } else if (action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
            // Do something when power disconnected
            System.out.println("power DISCONNECTED");
        }
    }
}
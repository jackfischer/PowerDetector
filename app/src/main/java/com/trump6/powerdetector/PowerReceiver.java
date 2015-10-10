package com.trump6.powerdetector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.trump6.powerdetector.ServerAPI;

public class PowerReceiver extends BroadcastReceiver {
    ServerAPI api;

    public PowerReceiver() {
        System.out.println("Inside the power receiver constructor");
        api = new ServerAPI();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("received EVENT!!:  ");
        String action = intent.getAction();
        System.out.println(action);

        if (action.equals(Intent.ACTION_POWER_CONNECTED)) {
            System.out.println("power CONNECTED");
            this.api.sendMessage("CONNECTED");
        }
        if (action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
            System.out.println("power DISCONNECTED");
            this.api.sendMessage("DISCONNECTED");
        }
        if (action.equals(Intent.ACTION_BATTERY_LOW)) {
            System.out.println("power LOW");
            this.api.sendMessage("LOW");
        }
    }

}

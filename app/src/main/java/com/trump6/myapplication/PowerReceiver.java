package com.trump6.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class PowerReceiver extends BroadcastReceiver {
    public PowerReceiver() {
        System.out.println("Inside the power receiver constructor");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        System.out.println("received EVENT!!:  ");

        String action = intent.getAction();
        System.out.println(action);

        if (action.equals(Intent.ACTION_POWER_CONNECTED)) {
            // Do something when power connected
            System.out.println("power CONNECTED");
            this.pingServer("CONNECTED");

        } else if (action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
            // Do something when power disconnected
            System.out.println("power DISCONNECTED");
            this.pingServer("DISCONNECTED");
        }

        if (action.equals(Intent.ACTION_BATTERY_LOW)) {
            //battery low
            System.out.println("power LOW");
            this.pingServer("LOW");
        }
    }


    public void pingServer(String situation) {

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://trump6.com:5000";
        RequestParams params = new RequestParams("situation", situation);
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("connection worked!!");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("connection failed");
            }
        };

        client.post(url, params, responseHandler);

    }
}

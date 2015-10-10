package com.trump6.powerdetector;

import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;

/**
 * Created by jack on 10/10/15.
 */

public class ServerAPI {
    AsyncHttpClient client;
    String url;
    AsyncHttpResponseHandler responseHandler;

    public ServerAPI() {
        client = new AsyncHttpClient();
        url = "http://trump6.com:5000";

        responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("connection worked!!");
                System.out.println(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("connection failed");
                System.out.println(responseBody);
            }
        };
    }

    public void sendMessage(String situation) {
        RequestParams params = new RequestParams("situation", situation);
        client.post(url, params, responseHandler);

    }

}

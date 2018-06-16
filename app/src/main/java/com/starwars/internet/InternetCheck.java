package com.starwars.internet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetCheck extends BroadcastReceiver {

    public static InternetCheckListener checkInternetListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        if (checkInternetListener != null) {
            checkInternetListener.onNetworkConnectionChanged(isConnected);
        }
    }
}

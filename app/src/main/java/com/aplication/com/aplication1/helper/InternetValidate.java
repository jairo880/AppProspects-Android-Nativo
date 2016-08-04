package com.aplication.com.aplication1.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jairo880 on 2/08/16.
 */
public class InternetValidate {

    private Context context;

    public InternetValidate(Context context) {

        this.context = context;
    }

    public InternetValidate() {

    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    return   networkInfo != null && networkInfo.isConnectedOrConnecting() && networkInfo.isAvailable() && networkInfo.isConnected();
    }


}

package com.example.mvptask.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Utilities {
    /**
     * show toast message
     *
     * @param message
     * @param context
     */
    public static void displayToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * check if the Application is connected or not
     *
     * @param context
     * @return
     */
    public static boolean checkIfApplicationIsConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null) {
            return activeNetInfo != null && activeNetInfo.isAvailable()
                    && activeNetInfo.isConnected();
        } else {
            return false;
        }
    }
}

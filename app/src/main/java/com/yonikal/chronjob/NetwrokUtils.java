package com.yonikal.chronjob;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by yonikal on 12/10/15.
 */
public class NetwrokUtils {

    public static boolean hasActiveConnection(Context context) {
        try {
            ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        } catch (Exception e) {
            return false;
        }
    }
}

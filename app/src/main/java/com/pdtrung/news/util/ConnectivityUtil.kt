package com.pdtrung.news.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build


object ConnectivityUtil {
    fun isConnected(context: Context?): Boolean{
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun internetType(context: Context?) {
        // Checking internet connectivity
        val connectivityMgr =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        var activeNetwork: NetworkInfo? = null
        if (connectivityMgr != null) {
            activeNetwork = connectivityMgr.activeNetworkInfo
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val nc = connectivityMgr.getNetworkCapabilities(connectivityMgr.activeNetwork)
                if (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    // connected to mobile data
                } else if (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    // connected to wifi
                }
            } else {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    // connected to mobile data
                }
            }
        }
    }
}
package com.dev.chacha.presentation.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build

class NetworkMonitor constructor(val context: Context) {

    private var networkCallback: NetworkCallback? = null
    private val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)
    private var isNetworkAvailable =
        networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    private var isWifiConnected =
        networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
    private var isCellularConnected =
        networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true


    fun startMonitoring() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()

        networkCallback = object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                // Perform necessary actions when network is available
                isNetworkAvailable = true
                isWifiConnected = true
                isCellularConnected = true

            }
            override fun onLost(network: Network) {
                // Perform necessary actions when network is lost
                isNetworkAvailable = false
                isWifiConnected = false
                isCellularConnected = false
            }
        }

        if (Build.VERSION.SDK_INT < 28) {
            cm.registerNetworkCallback(networkRequest, networkCallback as NetworkCallback)
        } else
            cm.registerDefaultNetworkCallback(networkCallback as NetworkCallback)
    }

    fun stopMonitoring() {
        networkCallback?.let { cm.unregisterNetworkCallback(it) }
    }
}

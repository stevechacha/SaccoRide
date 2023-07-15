package com.dev.chacha.presentation.networks

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext

sealed interface NetworkConnectionState{
    object Connected: NetworkConnectionState
    object Disconnected: NetworkConnectionState
    object Connecting: NetworkConnectionState
    object Disconnecting: NetworkConnectionState
    object Suspended: NetworkConnectionState
    object Unknown: NetworkConnectionState
    object Available : NetworkConnectionState
    object Unavailable : NetworkConnectionState
    object WifeAvailable : NetworkConnectionState
    object CellularAvailable : NetworkConnectionState
    object CellUnavailable : NetworkConnectionState
    object WifeUnavailable : NetworkConnectionState


}

private fun networkCallback(callback: (NetworkConnectionState) -> Unit): ConnectivityManager.NetworkCallback {
    return object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            callback(NetworkConnectionState.Available)
            callback(NetworkConnectionState.Connected)
            callback(NetworkConnectionState.WifeAvailable)
            callback(NetworkConnectionState.CellularAvailable)

        }

        override fun onLost(network: Network) {
            callback(NetworkConnectionState.Unavailable)
            callback(NetworkConnectionState.Disconnected)
            callback(NetworkConnectionState.CellUnavailable)
            callback(NetworkConnectionState.WifeUnavailable)
        }
    }
}

private fun getCurrentConnectivityState(connectivityManager: ConnectivityManager): NetworkConnectionState {
    val network = connectivityManager.activeNetwork

    val connected = connectivityManager.getNetworkCapabilities(network)
        ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false

    return if (connected) NetworkConnectionState.Available  else NetworkConnectionState.Unavailable
}
@ExperimentalCoroutinesApi
private fun Context.observeConnectivityAsFlow(): Flow<NetworkConnectionState> = callbackFlow {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val callback = networkCallback { connectionState ->
        trySend(connectionState)
    }

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .build()

    connectivityManager.registerNetworkCallback(networkRequest, callback)

    val currentState = getCurrentConnectivityState(connectivityManager)
    trySend(currentState)

    awaitClose {
        connectivityManager.unregisterNetworkCallback(callback)
    }
}

private val Context.currentConnectivityState: NetworkConnectionState
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return getCurrentConnectivityState(connectivityManager)
    }


@ExperimentalCoroutinesApi
@Composable
fun rememberConnectivityState(): State<NetworkConnectionState> {
    val context = LocalContext.current
    return produceState(initialValue = context.currentConnectivityState) {
        context.observeConnectivityAsFlow().collect {
            value = it
        }
    }
}


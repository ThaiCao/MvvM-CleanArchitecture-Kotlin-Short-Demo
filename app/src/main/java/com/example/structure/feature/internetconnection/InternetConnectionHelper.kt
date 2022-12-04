package com.example.structure.feature.internetconnection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.ContextCompat
import com.example.structure.network.NetworkConnection
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface InternetConnectionHelper {

    fun registerInternetConnection(onAvailable: (() -> Unit)?, onLost: (() -> Unit)?)

    fun unregisterInternetConnection()
}

class InternetConnectionHelperImpl(
    private val context: Context,
) : InternetConnectionHelper, KoinComponent {

    private val networkConnection: NetworkConnection by inject()

    private var connectivityManager: ConnectivityManager? = null

    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    var onAvailableListener: (() -> Unit)? = null

    var onLostListener: (() -> Unit)? = null

    override fun registerInternetConnection(onAvailable: (() -> Unit)?, onLost: (() -> Unit)?) {
        onAvailableListener = onAvailable
        onLostListener = onLost
        if (!networkConnection.isConnected()) {
            onLostListener?.invoke()
        }

        connectivityManager = ContextCompat.getSystemService(
            context,
            ConnectivityManager::class.java
        )
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                onAvailableListener?.invoke()
            }

            override fun onLost(network: Network) {
                onLostListener?.invoke()
            }
        }
        networkCallback?.let {
            connectivityManager?.registerNetworkCallback(
                NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build(),
                it
            )
        }
    }

    override fun unregisterInternetConnection() {
        onAvailableListener = null
        onLostListener = null
        networkCallback?.let {
            connectivityManager?.unregisterNetworkCallback(it)
        }
    }
}

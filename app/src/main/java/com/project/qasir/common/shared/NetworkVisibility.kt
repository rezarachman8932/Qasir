package com.project.qasir.common.shared

import android.content.Context
import android.net.ConnectivityManager

class NetworkVisibility(private val context: Context) {

    @Suppress("DEPRECATION")
    fun hasNetworkAvailable(): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        return network != null && network.isConnectedOrConnecting
    }

}
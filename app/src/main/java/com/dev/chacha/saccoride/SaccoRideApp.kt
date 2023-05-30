package com.dev.chacha.saccoride

import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import com.dev.chacha.presentation.util.network.NetworkMonitor
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SaccoRideApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }
    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    override fun registerComponentCallbacks(callback: ComponentCallbacks?) {
        super.registerComponentCallbacks(callback)
        NetworkMonitor(this).startMonitoring()
    }

    override fun unregisterComponentCallbacks(callback: ComponentCallbacks?) {
        super.unregisterComponentCallbacks(callback)
        NetworkMonitor(this).stopMonitoring()
    }

}
package com.dev.chacha.saccoride

import android.app.Application
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
}
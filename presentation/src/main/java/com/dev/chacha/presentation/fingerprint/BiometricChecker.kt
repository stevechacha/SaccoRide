package com.dev.chacha.presentation.fingerprint

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.activity.MainActivity
import com.dev.chacha.presentation.common.navigation.AuthScreen
import com.dev.chacha.presentation.common.navigation.DestinationGraph
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.P)
class BiometricChecker(
    private val authListener: AuthListener,
    val navController: NavController,
    val activity: ComponentActivity
) : BiometricPrompt.AuthenticationCallback() {

    private var cancellationSignal: CancellationSignal? = null

    private val authenticationCallback = @RequiresApi(Build.VERSION_CODES.P)
    object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
            notifyUser("Authentication Error $errorCode")
            super.onAuthenticationError(errorCode, errString)
        }

        override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
            super.onAuthenticationHelp(helpCode, helpString)
        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
//            navController.navigate(AuthScreen.PinLock.route)
        }
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
            notifyUser("Authentication Succeeded")
            super.onAuthenticationSucceeded(result)
            navController.navigate(DestinationGraph.HOME_SCREEN_ROUTE)
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun authenticate() {
        if (checkBiometricSupport()) {
            val biometricPrompt = BiometricPrompt
                .Builder(activity)
                .setTitle(activity.getString(R.string.sacco_app))
                .setDescription("Scan your fingerprint ")
                .setNegativeButton("CANCEL", activity.mainExecutor) { _, _ ->
                    notifyUser("Authentication cancelled")
//                    navController.navigate(AuthScreen.PinLock.route)

                }
                .build()
            biometricPrompt.authenticate(
                getCancellationSignal(),
                activity.mainExecutor,
                authenticationCallback
            )
        }
    }

    private fun checkBiometricSupport(): Boolean {
        val keyguardManager = activity.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isDeviceSecure) {
            notifyUser("lock screen security not enabled in the setting")
            return false
        }
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.USE_BIOMETRIC
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            notifyUser("Finger print authentication permission not enabled")
            return false
        }
        return activity.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Auth Cancelled via Signal")
        }
        return cancellationSignal as CancellationSignal
    }

    private fun notifyUser(message: String) {
        Timber.tag("BIOMETRIC").d(message)
    }

    interface AuthListener {
        fun onAuthSuccess(message: String)
        fun onAuthError(error: String)
    }


}
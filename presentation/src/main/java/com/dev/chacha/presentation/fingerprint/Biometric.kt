package com.dev.chacha.presentation.fingerprint
import android.Manifest
import android.app.Activity
import android.app.Application
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.activity.MainActivity
import com.dev.chacha.presentation.common.navigation.AuthScreen
import com.dev.chacha.presentation.common.navigation.Graph
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.P)
class Biometric(
    private val authListener: AuthListener,
    private val navController: NavController,
    private val activity: ComponentActivity
) : BiometricPrompt.AuthenticationCallback(), Application.ActivityLifecycleCallbacks {

    private var cancellationSignal: CancellationSignal? = null
    private var currentScreen: String? = null

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
            navController.navigate(AuthScreen.PinLock.route)
        }
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
            notifyUser("Authentication Succeeded")
            super.onAuthenticationSucceeded(result)
            if (currentScreen == null) {
                navController.navigate(Graph.HOME)
            } else {
                navController.navigate(currentScreen!!)
                currentScreen = null
            }
        }
    }

    init {
        activity.application.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityPaused(activity: Activity) {
        if (!activity.isFinishing) {
            currentScreen = navController.currentDestination?.id?.toString()
        }
    }

    override fun onActivityResumed(activity: Activity) {
        currentScreen = null
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            currentScreen = savedInstanceState.getString("currentScreen")
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        outState.putString("currentScreen", currentScreen)
    }

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityDestroyed(activity: Activity) {}

    @RequiresApi(Build.VERSION_CODES.R)
    fun authenticate() {
        if (checkBiometricSupport()) {
            val biometricPrompt = BiometricPrompt.Builder(activity)
                .setTitle("Authenticate with Biometrics")
                .setDescription("Scan your fingerprint")
                .setNegativeButton("USE PIN", activity.mainExecutor,
                    DialogInterface.OnClickListener { _, _ ->
                        notifyUser("Authentication cancelled")
                        navController.navigate(AuthScreen.PinLock.route)
                    })
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

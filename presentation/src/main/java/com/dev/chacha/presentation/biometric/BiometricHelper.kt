package com.dev.chacha.presentation.biometric

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import android.provider.Settings.Global.getString
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.dev.chacha.presentation.R
import com.dev.chacha.presentation.activity.MainActivity
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.P)
class BiometricHelper(private val authListener: AuthListener,val activity: ComponentActivity) :
    BiometricPrompt.AuthenticationCallback() {
    private var cancellationSignal: CancellationSignal? = null

    private val authenticationCallback: BiometricPrompt.AuthenticationCallback =
        @RequiresApi(Build.VERSION_CODES.P)
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
            }

            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)
            }
        }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkBiometricSupport(): Boolean {
        val keyGuardManager = activity.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (!keyGuardManager.isDeviceSecure) {
            return true
        }
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            return false
        }

        return activity.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    private fun launchBiometric() {
        if (checkBiometricSupport()) {
            val biometricPrompt = BiometricPrompt.Builder(activity)
                .apply {
                    setTitle("Login")
                    setSubtitle("Login to your Sacco account")
                    setDescription("Please use your fingerprint to login")
                    setConfirmationRequired(false)
                    setNegativeButton("Cancel", activity.mainExecutor) { _, _, ->

                    }
                }.build()

            biometricPrompt.authenticate(getCancellationSignal(), activity.mainExecutor, authenticationCallback)
        }
    }



    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            "Anthencation failed"
        }

        return cancellationSignal as CancellationSignal
    }

    interface AuthListener {
        fun onAuthError(error: String)
        fun onAuthSuccess(message: String)
    }
}
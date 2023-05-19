package com.dev.chacha.presentation.permissions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

class PermissionsViewModel : ViewModel() {
   /* private val _permissionsState = MutableLiveData<PermissionState>()
    val permissionsState: LiveData<PermissionState> = _permissionsState

    @OptIn(ExperimentalPermissionsApi::class)
    fun requestPermissions() {
        _permissionsState.value = PermissionState.Requesting
    }

    fun onPermissionResult(permissions: List<String>, grantResults: List<Int>) {
        val granted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
        _permissionsState.value = if (granted) {
            PermissionState.Granted
        } else {
            PermissionState.Denied
        }
    }*/
}

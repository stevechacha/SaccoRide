package com.dev.chacha.presentation.permissions

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.chacha.presentation.extensions.getCallLogs
import com.dev.chacha.presentation.extensions.getContactsList
import com.dev.chacha.presentation.extensions.getMessageList
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermissions(
    viewModel: PermissionsViewModel = viewModel(),
    onClick: () -> Unit,
) {

    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
        )
    )
    val context = LocalContext.current

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    permissionsState.launchMultiplePermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.addObserver(observer)
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        permissionsState.permissions.forEach { perms ->
            when (perms.permission) {
                Manifest.permission.READ_SMS -> {
                    when {
                        perms.hasPermission -> {
                            val smsCursor = getMessageList(context)
                            val gson = GsonBuilder().setPrettyPrinting().create()
                            val userSms = gson.toJson(smsCursor)
                            val userSmsString: String = userSms.toString()
                            val fileSms = File(context.filesDir, "SmsList.json")
                            val fileWriterSms = FileWriter(fileSms)
                            val bufferedWriterSms = BufferedWriter(fileWriterSms)
                            bufferedWriterSms.write(userSmsString)
                            bufferedWriterSms.close()
                        }
                        perms.shouldShowRationale -> {
                            Text(
                                text = "Sms persmisson is needed " +
                                        "to access the camera"
                            )
                        }

                        perms.isPermanentlyDenied() -> {
                            Text(
                                text = "Sms persmisson is Permanantly Denied" +
                                        "you can enable it into app setting"
                            )
                        }
                    }
                }

                Manifest.permission.READ_CALL_LOG -> {
                    when {
                        perms.hasPermission -> {
                            //calls
                            val callLogsList = getCallLogs(context)
                            viewModel.viewModelScope.launch(Dispatchers.IO) {
                                val gson = GsonBuilder().setPrettyPrinting().create()
                                val userCallLogs = gson.toJson(callLogsList)
                                val userCallLogsString: String = userCallLogs.toString()
                                val fileCall = File(context.filesDir, "CalllogList.json")
                                val fileWriterCall = FileWriter(fileCall)
                                val bufferedWriterCall = BufferedWriter(fileWriterCall)
                                bufferedWriterCall.write(userCallLogsString)
                                bufferedWriterCall.close()
                            }
                        }

                        perms.shouldShowRationale -> {
                            Text(
                                text = "Calls persmisson is needed " +
                                        "to access the microphone"
                            )
                        }

                        perms.isPermanentlyDenied() -> {
                            Text(
                                text = "Calls persmisson is Permanantly Denied" +
                                        "you can enable it into app setting"
                            )
                        }
                    }
                }

                Manifest.permission.READ_CONTACTS -> {
                    when {
                        perms.hasPermission -> {
                            //contacts
                            val contactList = getContactsList(context)
                            //contacts
                            val gson = GsonBuilder().setPrettyPrinting().create()
                            val userContact = gson.toJson(contactList)
                            val userContactString: String = userContact.toString()
                            val fileContact = File(context.filesDir, "ContactList.json")
                            val fileWriterContact = FileWriter(fileContact)
                            val bufferedWriterContact = BufferedWriter(fileWriterContact)
                            bufferedWriterContact.write(userContactString)
                            bufferedWriterContact.close()
                        }

                        perms.shouldShowRationale -> {
                            Text(
                                text = "Contact persmisson is needed " +
                                        "to access the microphone"
                            )
                        }

                        perms.isPermanentlyDenied() -> {
                            Text(
                                text = "Contact persmisson is Permanantly Denied" +
                                        "you can enable it into app setting"
                            )
                        }
                    }

                }
            }
        }

        Button(onClick = { permissionsState.launchMultiplePermissionRequest() }) {
            Text(text = "Click Here for permission")
        }
    }

}




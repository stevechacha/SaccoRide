package com.dev.chacha.presentation.permissions

import android.os.Parcelable
import android.provider.CallLog
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class CallLogs(
    val callId: String,
    val phoneId: String,
    val phoNumber: String,
    val callType: Int,
    val callDuration: String,
    val callDate: String,
    val sim: String,
    val callTime: String,
): Parcelable
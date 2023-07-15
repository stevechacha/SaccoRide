package com.dev.chacha.presentation.extensions

import android.content.Context
import android.content.pm.ApplicationInfo
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.provider.CallLog
import android.provider.ContactsContract
import android.provider.Telephony
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import com.dev.chacha.presentation.contactList.Contact
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.parcelize.Parcelize
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun getInitials(name: String): String {
    val names = name.split(" ")
    val initials = if (names.size >= 2) {
        names[0].trim().first().toString().trim() + names[1].trim().first().toString().trim()
    } else {
        names[0].trim().first().toString().trim()
    }
    return initials.uppercase()
}


@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentDateTime(): String {
    val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:a")
    return currentDateTime.toJavaLocalDateTime().format(formatter)
}

fun formatDateTime(date: String, time: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
    val dateTimeString = "$date $time"
    val parsedDate = inputFormat.parse(dateTimeString)
    return parsedDate?.let { outputFormat.format(it) } ?: ""
}


fun formatContact(contact: String): String {
    val formattedContact: String =when {
        contact.startsWith("254") -> {
            val prefix = contact.substring(0, 6)
            val lastThreeDigits = contact.substring(contact.length - 3)
            "$prefix***$lastThreeDigits"
        }
        contact.startsWith("07") -> {
            val prefix = contact.substring(0, 4)
            val lastThreeDigits = contact.substring(contact.length - 3)
            "$prefix***$lastThreeDigits"
        }
        contact.startsWith("+254") -> {
            val prefix = contact.substring(0, 7)
            val lastThreeDigits = contact.substring(contact.length - 3)
            "$prefix***$lastThreeDigits"
        }
        contact.startsWith("01") -> {
            val prefix = contact.substring(0, 4)
            val lastThreeDigits = contact.substring(contact.length - 3)
            "$prefix***$lastThreeDigits"
        }

        else -> contact
    }
    return formattedContact
}



fun formatDate(date: String): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
    val parsedDate = inputFormat.parse(date)
    return parsedDate?.let { outputFormat.format(it) }
}


fun getContactsList(context: Context): List<Contact> {
    val contacts = mutableStateListOf<Contact>()
    val cursor = context.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null
    )

    cursor?.use {
        while (it.moveToNext()) {
            val contactName =
                it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val contactPhoneNumber =
                it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val contactEmail =
                it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS)
            val contactId = it.getColumnIndex(ContactsContract.Contacts._ID)

            val name = it.getString(contactName)
            val phoneNumber = it.getString(contactPhoneNumber)
            val email = it.getString(contactEmail)
            val numberId = it.getString(contactId)

            // Remove spaces, hyphens, and parentheses from the phone number
            val cleanedPhoneNumber = phoneNumber.replace("[\\s-()]".toRegex(), "")

            // Check if the phone number has more than 10 digits
            if (cleanedPhoneNumber.length > 10 && cleanedPhoneNumber.startsWith("+")) {
                val formattedPhoneNumber = "0${cleanedPhoneNumber.substring(4)}"
                contacts.add(Contact(name, formattedPhoneNumber, email, numberId))
                if (formattedPhoneNumber.length<10) {
                    val secondFormatted = "0${formattedPhoneNumber}"
                    contacts.add(Contact(name, secondFormatted, email, numberId))
                    if (secondFormatted.length<10) {
                        val thirdFormatted = "0${secondFormatted}"
                        contacts.add(Contact(name, thirdFormatted, email, numberId))
                    }
                }
            } else {
                contacts.add(Contact(name, cleanedPhoneNumber, email, numberId))
            }
        }
    }
    cursor?.close()

    // Remove duplicates and sort by name
    return contacts.sortedBy { it.name }
}

@Parcelize
data class Message(
    val address: String?,
    val body: String,
    val date: String,
    val time: String,
    val id: String,
): Parcelable

fun getMessageList(context: Context){
    val messageList = arrayListOf<Message>()
    val cursor3 = context.contentResolver.query(
        Telephony.Sms.CONTENT_URI,
        null, null, null, null
    )

    while (cursor3 != null && cursor3.moveToNext()) {
        //Date
        val smsDate =
            cursor3.getString(cursor3.getColumnIndexOrThrow(Telephony.Sms.DATE)).toLong()
        val date = Date()
        date.time = smsDate
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateString = simpleDateFormat.format(date)
        // Time
        val smsTime =
            cursor3.getString(cursor3.getColumnIndexOrThrow(Telephony.Sms.DATE)).toLong()
        val time = Date()
        time.time = smsTime
        val simpleTimeFormat = SimpleDateFormat("HH:mm:ss")
        val timeString = simpleTimeFormat.format(time)
        // Sender address
        val address = cursor3.getString(cursor3.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
        // Message Body
        val body =
            cursor3.getString(cursor3.getColumnIndexOrThrow(Telephony.Sms.BODY)).toString()
        // Message Id
        val id = cursor3.getString(cursor3.getColumnIndexOrThrow(Telephony.Sms._ID))

        messageList.add(Message(address, body, dateString, timeString, id))
    }
    cursor3?.close()

}


@Parcelize

data class MobileApps(
    val appName: String
): Parcelable
fun getMobileApps(context: Context){
    //apps
    val appList = arrayListOf<MobileApps>()
    val packageManager = context.packageManager
    val applicationList = packageManager.getInstalledApplications(0)
    val it: Iterator<ApplicationInfo> = applicationList.iterator()
    while (it.hasNext()) {
        val appname = packageManager.getApplicationLabel(it.next()).toString()
        appList.add(
            MobileApps(
                appname
            )
        )
    }
}

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

fun getCallLogs(context: Context){
    //calls
    val callLogsList = arrayListOf<CallLogs>()
    val cursor = context.contentResolver.query(CallLog.Calls.CONTENT_URI,
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

        val date = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE))
        val datee = Date(date.toLong())
        val ft = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        // Call time
        val callTime = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE)).toLong()
        val time = Date()
        time.time = callTime
        val simpleTimeFormat = SimpleDateFormat("HH:mm:ss")
        val timeString = simpleTimeFormat.format(time)

        val simwhat = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.VIA_NUMBER))

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

        callLogsList.add(CallLogs(callId, phoneId, phoneNumber, callType, callDuration, ft.format(datee), simwhat, timeString))
    }
    cursor?.close()

}

@Parcelize
data class SMSList(
    val id: String,
    val address: String,
    val body: String,
    val date: String,
    val readState: String,
    val folderName: String,
    val contactName: String,
    val contactId: String
): Parcelable

private fun getSMSList(context: Context): List<SMSList> {
    val smsList = mutableListOf<SMSList>()

    val cursor = context.contentResolver.query(
        Uri.parse("content://sms/inbox"), null, null, null, null
    )

    cursor?.use {
        while (it.moveToNext()) {
            val id = it.getString(it.getColumnIndexOrThrow("_id"))
            val address = it.getString(it.getColumnIndexOrThrow("address"))
            val body = it.getString(it.getColumnIndexOrThrow("body"))
            val date = it.getString(it.getColumnIndexOrThrow("date"))
            val readState = it.getString(it.getColumnIndexOrThrow("read"))
            val folderName = it.getString(it.getColumnIndexOrThrow("type"))
            val contactName = it.getString(it.getColumnIndexOrThrow("person"))
            val contactId = it.getString(it.getColumnIndexOrThrow("contact_id"))

            smsList.add(
                SMSList(
                    id,
                    address,
                    body,
                    date,
                    readState,
                    folderName,
                    contactName,
                    contactId
                )
            )
        }
    }
    cursor?.close()

    Timber.tag("SMSList").d(smsList.toString())

    return smsList

}

/*private fun formatContact(contact: String): String {
    val formattedContact: String =when {
        contact.startsWith("254") -> {
            val prefix = contact.substring(0, 6)
            val lastThreeDigits = contact.substring(contact.length - 3)
            "$prefix***$lastThreeDigits"
        }
        contact.startsWith("07") -> {
            val prefix = contact.substring(0, 4)
            val lastThreeDigits = contact.substring(contact.length - 3)
            "$prefix***$lastThreeDigits"
        }
        contact.startsWith("+254") -> {
            val prefix = contact.substring(0, 7)
            val lastThreeDigits = contact.substring(contact.length - 3)
            "$prefix***$lastThreeDigits"
        }
        contact.startsWith("01") -> {
            val prefix = contact.substring(0, 4)
            val lastThreeDigits = contact.substring(contact.length - 3)
            "$prefix***$lastThreeDigits"
        }

        else -> contact
    }
    return formattedContact
}*/

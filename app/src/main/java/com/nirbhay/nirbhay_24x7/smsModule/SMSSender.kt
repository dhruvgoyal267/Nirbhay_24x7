package com.nirbhay.nirbhay_24x7.smsModule

import android.telephony.SmsManager
import com.nirbhay.nirbhay_24x7.models.Contact

fun sendSms(contacts: List<Contact>, msg: String) {
    val smsManager = SmsManager.getDefault()
    contacts.iterator().forEach { contact ->
        smsManager.sendTextMessage(contact.contactNumber, null, msg, null, null)
    }
}

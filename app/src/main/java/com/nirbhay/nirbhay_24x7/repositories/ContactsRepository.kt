package com.nirbhay.nirbhay_24x7.repositories

import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nirbhay.nirbhay_24x7.models.Contact

class ContactsRepository {
    var contacts = MutableLiveData<List<Contact>>()

    fun loadContactsFromPhone(activity: AppCompatActivity): LiveData<List<Contact>> {
        val con = fetchContacts(activity)
        contacts.value = con
        return contacts
    }

    private fun fetchContacts(activity: AppCompatActivity): ArrayList<Contact> {
        val contactsList = ArrayList<Contact>()
        val cursor = activity.contentResolver
            .query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null
            )
        while (cursor?.moveToNext()!!) {
            val name =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            contactsList.add(Contact(name, number, false))
        }
        cursor.close()
        return contactsList
    }

    fun loadContactsFromDatabase(): LiveData<List<Contact>> {
        return contacts
    }
}
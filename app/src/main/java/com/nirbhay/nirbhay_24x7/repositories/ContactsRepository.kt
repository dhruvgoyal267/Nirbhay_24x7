package com.nirbhay.nirbhay_24x7.repositories

import android.content.Context
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.room.Dao
import com.nirbhay.nirbhay_24x7.database.ContactDatabase
import com.nirbhay.nirbhay_24x7.database.ContactsDao
import com.nirbhay.nirbhay_24x7.models.Contact
import java.util.concurrent.Flow

class ContactsRepository {
    fun loadContactsFromPhone(activity: AppCompatActivity): List<Contact> {
        return fetchContacts(activity)
    }

    private fun fetchContacts(
        activity: AppCompatActivity,
      ): ArrayList<Contact> {
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


    suspend fun saveContactsToDatabase(contacts: List<Contact>, context: Context) {
        val db = ContactDatabase.getDatabase(context)
        val contactDao = db.contactsDao()
        contactDao.insertContacts(contacts)
    }

    suspend fun loadContactsFromDatabase(context: Context): List<Contact> {
        val db = ContactDatabase.getDatabase(context)
        val contactDao = db.contactsDao()
        return contactDao.getContacts()
    }
}
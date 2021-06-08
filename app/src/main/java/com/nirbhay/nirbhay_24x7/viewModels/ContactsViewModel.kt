package com.nirbhay.nirbhay_24x7.viewModels

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirbhay.nirbhay_24x7.models.Contact
import com.nirbhay.nirbhay_24x7.repositories.ContactsRepository
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ContactsViewModel : ViewModel() {
    private val repository: ContactsRepository = ContactsRepository()
    var contacts: MutableLiveData<List<Contact>> = MutableLiveData()
    fun getContactsFromPhone(activity: AppCompatActivity, name: String) {
        var myContacts = repository.loadContactsFromPhone(activity)
        if (name.isNotEmpty()) {
            myContacts = myContacts.filter {
                it.contactName.toLowerCase(Locale.getDefault())
                    .contains(name.toLowerCase(Locale.getDefault()))
            }
        }
        contacts.value = myContacts
    }

    fun saveContacts(contacts: List<Contact>, context: Context) {
        viewModelScope.launch {
            repository.saveContactsToDatabase(contacts, context)
        }
    }

    fun getContactsFromDataBase(context: Context) {
        viewModelScope.launch {
            contacts.value = repository.loadContactsFromDatabase(context)
        }
    }
}
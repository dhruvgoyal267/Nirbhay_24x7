package com.nirbhay.nirbhay_24x7.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nirbhay.nirbhay_24x7.models.Contact
import com.nirbhay.nirbhay_24x7.repositories.ContactsRepository

class ContactsViewModel : ViewModel() {
    private val repository: ContactsRepository = ContactsRepository()

    fun getContactsFromPhone(activity: AppCompatActivity): LiveData<List<Contact>> {
        return repository.loadContactsFromPhone(activity)
    }

    fun getContactsFromDataBase(): LiveData<List<Contact>> {
        return repository.loadContactsFromDatabase()
    }
}
package com.nirbhay.nirbhay_24x7.view.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nirbhay.nirbhay_24x7.R
import com.nirbhay.nirbhay_24x7.databinding.ActivityLoadContactsBinding
import com.nirbhay.nirbhay_24x7.models.Contact
import com.nirbhay.nirbhay_24x7.utilities.setContactView
import com.nirbhay.nirbhay_24x7.view.activities.LoadContactsActivity.Static.selectedContact
import com.nirbhay.nirbhay_24x7.viewModels.ContactsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoadContactsActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoadContactsBinding
    lateinit var menu: Menu

    object Static {
        lateinit var selectedContact: ArrayList<Contact>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        selectedContact = ArrayList()
        loadData()
    }

    private fun checkForSelectedContacts() {
        val coroutineScope = CoroutineScope(Dispatchers.Default)
        coroutineScope.launch {
            while (true) {
                runOnUiThread {
                    menu.findItem(R.id.select_item).isVisible = selectedContact.isNotEmpty()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu != null)
            this.menu = menu
        menuInflater.inflate(R.menu.activity_load_contacts, menu)
        checkForSelectedContacts()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.select_item) {

        }
        return true
    }

    private fun loadData() {
        val viewModel = ContactsViewModel()
        viewModel.getContactsFromPhone(this).observe(this, {
            binding.contacts.setContactView(it)
        })
    }
}
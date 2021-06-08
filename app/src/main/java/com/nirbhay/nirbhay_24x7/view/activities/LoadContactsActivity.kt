package com.nirbhay.nirbhay_24x7.view.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.*
import androidx.lifecycle.ViewModelProviders
import com.nirbhay.nirbhay_24x7.R
import com.nirbhay.nirbhay_24x7.databinding.ActivityLoadContactsBinding
import com.nirbhay.nirbhay_24x7.models.Contact
import com.nirbhay.nirbhay_24x7.utilities.checkForSelectedContacts
import com.nirbhay.nirbhay_24x7.utilities.setContactView
import com.nirbhay.nirbhay_24x7.view.activities.LoadContactsActivity.Static.selectedContact
import com.nirbhay.nirbhay_24x7.viewModels.ContactsViewModel

class LoadContactsActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoadContactsBinding
    lateinit var menu: Menu
    lateinit var viewModel: ContactsViewModel

    object Static {
        var selectedContact = ArrayList<Contact>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this).get(ContactsViewModel::class.java)
        viewModel.getContactsFromPhone(this, "")
        viewModel.contacts.observe(this, {
            binding.contacts.setContactView(it, true)
        })


        binding.searchContact.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null)
                    viewModel.getContactsFromPhone(this@LoadContactsActivity, query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null)
                    viewModel.getContactsFromPhone(this@LoadContactsActivity, newText)
                return true
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            this.menu = menu
            menuInflater.inflate(R.menu.activity_load_contacts, menu)
            checkForSelectedContacts(menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.select_item) {
            viewModel.saveContacts(selectedContact, this)
            finish()
        }
        return true
    }
}

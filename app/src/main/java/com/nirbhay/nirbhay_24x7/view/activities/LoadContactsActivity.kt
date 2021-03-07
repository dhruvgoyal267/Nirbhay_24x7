package com.nirbhay.nirbhay_24x7.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nirbhay.nirbhay_24x7.databinding.ActivityLoadContactsBinding
import com.nirbhay.nirbhay_24x7.utilities.setContactView
import com.nirbhay.nirbhay_24x7.viewModels.ContactsViewModel

class LoadContactsActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoadContactsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
    }

    private fun loadData() {
        val viewModel = ContactsViewModel()
        viewModel.getContactsFromPhone(this).observe(this, {
            binding.contacts.setContactView(it)
        })
    }
}
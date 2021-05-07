package com.nirbhay.nirbhay_24x7.adapters

import com.nirbhay.nirbhay_24x7.R
import com.nirbhay.nirbhay_24x7.databinding.ContactLayoutBinding
import com.nirbhay.nirbhay_24x7.models.Contact
import com.nirbhay.nirbhay_24x7.view.activities.LoadContactsActivity.Static.selectedContact
import com.xwray.groupie.databinding.BindableItem

class ContactAdapter(private val contact: Contact) : BindableItem<ContactLayoutBinding>() {
    override fun bind(viewBinding: ContactLayoutBinding, position: Int) {
        viewBinding.contact = contact
        viewBinding.selectSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                selectedContact.add(contact)
            else
                selectedContact.remove(contact)
        }
    }


    override fun getLayout() = R.layout.contact_layout
}
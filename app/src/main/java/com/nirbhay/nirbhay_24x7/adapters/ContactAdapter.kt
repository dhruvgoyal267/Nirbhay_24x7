package com.nirbhay.nirbhay_24x7.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nirbhay.nirbhay_24x7.R
import com.nirbhay.nirbhay_24x7.database.ContactDatabase
import com.nirbhay.nirbhay_24x7.databinding.ContactLayoutBinding
import com.nirbhay.nirbhay_24x7.models.Contact
import com.nirbhay.nirbhay_24x7.utilities.changeVisibility
import com.nirbhay.nirbhay_24x7.utilities.showMsg
import com.nirbhay.nirbhay_24x7.view.activities.LoadContactsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class ContactAdapter(private val isChecked: Boolean) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private lateinit var context: Context
    private var contacts = ArrayList<Contact>()

    class ContactViewHolder(binding: ContactLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val bin = binding
    }


    fun updateContacts(_contacts: ArrayList<Contact>) {
        this.contacts = _contacts
        notifyDataSetChanged()
    }

    private fun deleteContact(pos: Int) {
        val c = contacts[pos]
        context.showMsg("${c.contactName} is deleted successfully")
        contacts.removeAt(pos)
        notifyItemRemoved(pos)
        val contactDao = ContactDatabase.getDatabase(context).contactsDao()
        runBlocking(Dispatchers.IO) {
            contactDao.deleteContact(c)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.contact_layout, parent, false)
        return ContactViewHolder(ContactLayoutBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bin.contact = contacts[position]

        if (!isChecked) {
            holder.bin.selectSwitch.changeVisibility()
            holder.bin.root.setOnLongClickListener {
                deleteContact(position)
                true
            }
        } else {
            holder.bin.selectSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    LoadContactsActivity.Static.selectedContact.add(contacts[position])
                    contacts[position].isSelected = true
                } else {
                    LoadContactsActivity.Static.selectedContact.remove(contacts[position])
                    contacts[position].isSelected = false
                }
            }
        }
    }

    override fun getItemCount(): Int = contacts.size
}
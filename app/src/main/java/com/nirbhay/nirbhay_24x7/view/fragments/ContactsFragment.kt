package com.nirbhay.nirbhay_24x7.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nirbhay.nirbhay_24x7.R
import com.nirbhay.nirbhay_24x7.databinding.FragmentContactsBinding
import com.nirbhay.nirbhay_24x7.utilities.startNewActivity
import com.nirbhay.nirbhay_24x7.view.activities.LoadContactsActivity


class ContactsFragment : Fragment() {
    lateinit var binding: FragmentContactsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContactsBinding.bind(view)
        binding.addContactBtn.setOnClickListener {
            activity?.startNewActivity(LoadContactsActivity::class.java)
        }
    }
}
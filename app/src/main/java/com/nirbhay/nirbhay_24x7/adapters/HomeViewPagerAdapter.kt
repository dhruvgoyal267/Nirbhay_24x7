package com.nirbhay.nirbhay_24x7.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nirbhay.nirbhay_24x7.view.fragments.ContactsFragment
import com.nirbhay.nirbhay_24x7.view.fragments.HomeFragment

class HomeViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    //function to return the tabs count of the view pager on home screen
    override fun getItemCount(): Int {
        return 2
    }


    //function used to create fragments according to the position of the selected tab
    override fun createFragment(position: Int): Fragment {
        if (position == 1)
            return ContactsFragment()
        return HomeFragment()
    }
}
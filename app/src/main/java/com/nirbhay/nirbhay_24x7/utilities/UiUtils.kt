package com.nirbhay.nirbhay_24x7.utilities

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.nirbhay.nirbhay_24x7.adapters.ContactAdapter
import com.nirbhay.nirbhay_24x7.adapters.HomeViewPagerAdapter
import com.nirbhay.nirbhay_24x7.models.Contact
import com.nirbhay.nirbhay_24x7.view.activities.HomeActivity
import com.xwray.groupie.GroupieAdapter


fun AppCompatActivity.setUpHomeViewPager() {
    val mAdapter = HomeViewPagerAdapter(supportFragmentManager, lifecycle)
    val binding = HomeActivity.ActivityObject.binding
    binding.tabLayout.apply {
        addTab(this.newTab().setText("Home"))
        addTab(this.newTab().setText("Contacts"))
        //setting up tab selector
        this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position != null)
                    binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
    binding.viewPager.apply {
        adapter = mAdapter
        //setting up swipe listener on Home Page
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.apply {
                    this.selectTab(this.getTabAt(position))
                }
            }
        })
    }
}


fun RecyclerView.setContactView(list: List<Contact>?) {
    val mAdapter = GroupieAdapter()
    if (list != null) {
        for (c in list)
            mAdapter.add(ContactAdapter(c))
    }
    setHasFixedSize(true)
    layoutManager = LinearLayoutManager(context)
    adapter = mAdapter
}


fun View.changeVisibility() {
    visibility = if (isVisible)
        View.GONE
    else
        View.VISIBLE
}

fun Context.showMsg(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.showSnackBar(msg: String) {

}




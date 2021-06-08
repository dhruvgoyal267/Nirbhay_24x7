package com.nirbhay.nirbhay_24x7.utilities

import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.nirbhay.nirbhay_24x7.R
import com.nirbhay.nirbhay_24x7.adapters.ContactAdapter
import com.nirbhay.nirbhay_24x7.adapters.HomeViewPagerAdapter
import com.nirbhay.nirbhay_24x7.databinding.ActivityMainBinding
import com.nirbhay.nirbhay_24x7.models.Contact
import com.nirbhay.nirbhay_24x7.view.activities.LoadContactsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


const val PERMISSION_CODE = 101
private val permissions = listOf(
    android.Manifest.permission.READ_CONTACTS,
    android.Manifest.permission.ACCESS_FINE_LOCATION,
    android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.SEND_SMS
)

fun AppCompatActivity.setUpHomeViewPager(binding: ActivityMainBinding) {
    val mAdapter = HomeViewPagerAdapter(supportFragmentManager, lifecycle)
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


fun RecyclerView.setContactView(list: List<Contact>?, isChecked: Boolean) {
    val mAdapter = ContactAdapter(isChecked)
    mAdapter.updateContacts(ArrayList(list))
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

fun AppCompatActivity.showSplashScreen(binding: ActivityMainBinding) {
    if (this.hasPermissions()) {
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            binding.splashScreen.changeVisibility()
            binding.mainScreen.changeVisibility()
            supportActionBar?.show()
        }, 3000)
    } else {
        ActivityCompat.requestPermissions(this, permissions.toTypedArray(), PERMISSION_CODE)
    }
}


fun AppCompatActivity.checkPermissionResult(requestCode: Int, grantResults: IntArray): Boolean {
    if (requestCode == PERMISSION_CODE) {
        if (grantResults.isNotEmpty()) {
            grantResults.iterator().forEach { result ->
                if (result == PackageManager.PERMISSION_DENIED) {
                    this.showMsg("Kindly grant permissions for smooth working of the application")
                    return false
                }
            }
            return true
        }
        return false
    }
    return false
}

private fun AppCompatActivity.hasPermissions(): Boolean {
    permissions.forEach { permission ->
        if (ActivityCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_DENIED
        )
            return false
    }
    return true
}


fun AppCompatActivity.checkForSelectedContacts(menu: Menu) {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    coroutineScope.launch(Dispatchers.Default) {
        while (true) {
            runOnUiThread {
                menu.findItem(R.id.select_item).isVisible =
                    LoadContactsActivity.Static.selectedContact.isNotEmpty()
            }
            delay(500)
        }
    }
}




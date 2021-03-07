package com.nirbhay.nirbhay_24x7.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nirbhay.nirbhay_24x7.databinding.ActivityMainBinding
import com.nirbhay.nirbhay_24x7.shakerModule.ShakerService
import com.nirbhay.nirbhay_24x7.utilities.setUpHomeViewPager
import com.nirbhay.nirbhay_24x7.view.activities.HomeActivity.ActivityObject.binding

class HomeActivity : AppCompatActivity() {
    object ActivityObject {
        lateinit var binding: ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding view with the variable "binding"
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewpager setting up
        this.setUpHomeViewPager()

        Intent(this, ShakerService::class.java).also { intent ->
            startService(intent)
        }
    }
}
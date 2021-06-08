package com.nirbhay.nirbhay_24x7.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nirbhay.nirbhay_24x7.databinding.ActivityMainBinding
import com.nirbhay.nirbhay_24x7.utilities.checkPermissionResult
import com.nirbhay.nirbhay_24x7.utilities.setUpHomeViewPager
import com.nirbhay.nirbhay_24x7.utilities.showSplashScreen

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        //binding view with the variable "binding"
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //show SplashScreen
        this.showSplashScreen(binding)

        //viewpager setting up
        this.setUpHomeViewPager(binding)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (this.checkPermissionResult(requestCode, grantResults))
            showSplashScreen(binding)
    }
}
package com.nirbhay.nirbhay_24x7.utilities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.checkForContactPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
    )
        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 101)
}

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        startActivity(it)
    }
}

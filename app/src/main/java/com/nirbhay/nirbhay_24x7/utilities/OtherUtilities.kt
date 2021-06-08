package com.nirbhay.nirbhay_24x7.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent

private const val SHAKE_DETECT = "isEnabled"
private const val SHARED_PREF = "Shake Detector"

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        startActivity(it)
    }
}

fun Context.setShakeEnabled(isEnabled: Boolean) {
    val editor =
        getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).edit()
    editor.putBoolean(SHAKE_DETECT, isEnabled)
    editor.apply()
}

fun Context.isShakeEnabled(): Boolean {
    return getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).getBoolean(
        SHAKE_DETECT,
        false
    )
}




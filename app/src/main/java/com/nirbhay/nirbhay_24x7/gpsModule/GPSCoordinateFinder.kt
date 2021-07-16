package com.nirbhay.nirbhay_24x7.gpsModule

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.nirbhay.nirbhay_24x7.database.ContactDatabase
import com.nirbhay.nirbhay_24x7.smsModule.sendSms
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.util.*


class GPSCoordinateFinder(private val context: Context) {
    var client: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)


    fun sendUpdatedLocation() {
        if (checkPermission()) {
            return
        }
        client.lastLocation.addOnSuccessListener {
            val db = ContactDatabase.getDatabase(context)
            val contactDao = db.contactsDao()
            runBlocking(Dispatchers.Default) {
                val geoCoder = Geocoder(context, Locale.getDefault())
                try {
                    val addresses: List<Address> =
                        geoCoder.getFromLocation(it.latitude, it.longitude, 1)
                    val address: String = addresses[0].getAddressLine(0)
                    sendSms(
                        contactDao.getContacts(),
                        "There is an emergency, I am at $address\nReach me at http://maps.google.com/maps?q=${it.latitude},${it.longitude}"
                    )
                } catch (e: Exception) {
                }
            }
        }
    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    }
}

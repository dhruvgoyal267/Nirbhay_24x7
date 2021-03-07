package com.nirbhay.nirbhay_24x7.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    var contactName: String, var contactNumber: String, var isSelected: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

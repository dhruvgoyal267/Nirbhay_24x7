package com.nirbhay.nirbhay_24x7.database

import androidx.room.*
import com.nirbhay.nirbhay_24x7.models.Contact
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contacts: List<Contact>)

    @Query("SELECT * FROM Contact")
    suspend fun getContacts(): List<Contact>

    @Delete
    suspend fun deleteContact(contact: Contact)
}
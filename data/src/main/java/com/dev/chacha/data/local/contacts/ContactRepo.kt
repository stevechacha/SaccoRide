package com.dev.chacha.data.local.contacts

import com.dev.chacha.data.repo.mappers.toContact
import com.dev.chacha.data.repo.mappers.toContactEntity
import com.dev.chacha.domain.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactRepository(private val contactDao: ContactDao) {
    fun getContacts(): List<Contact> {
        return contactDao.getAllContacts().map { it.toContact() }
    }

    suspend fun saveContacts(contactEntities: List<Contact>) {
        contactDao.insertContacts(contactEntities.map { it.toContactEntity() })
    }
}


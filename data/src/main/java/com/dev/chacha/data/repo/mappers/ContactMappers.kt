package com.dev.chacha.data.repo.mappers

import com.dev.chacha.data.local.contacts.ContactEntity
import com.dev.chacha.domain.model.Contact

internal fun ContactEntity.toContact(): Contact {
    return Contact(
        phoneNumber = phoneNumber,
        name = name,
        email = email,
        numberId = id.toString()
    )
}

fun Contact.toContactEntity(): ContactEntity{
    return ContactEntity(
        id = numberId.toLong(),
        name = name,
        email= email,
        phoneNumber = phoneNumber
    )
}
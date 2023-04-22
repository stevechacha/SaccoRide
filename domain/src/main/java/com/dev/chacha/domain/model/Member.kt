package com.dev.chacha.domain.model


/*
Member class:
    name (e.g. string)
    contact information (e.g. phone number, email address, mailing address) (e.g. string)
    membership status (e.g. active, inactive) (e.g. string or enumerated type)
    membership ID (e.g. string or integer)

*/

data class Member(
    val email: String,
    val contact: String
)

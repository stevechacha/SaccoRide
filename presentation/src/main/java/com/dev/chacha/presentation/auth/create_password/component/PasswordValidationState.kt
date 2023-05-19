package com.dev.chacha.presentation.auth.create_password.component

data class PasswordValidationState(
    val hasMinimum: Boolean = false,
    val hasCapitalizedLetter: Boolean = false,
    val hasSpecialCharacter: Boolean = false,
    val successful: Boolean = false
)

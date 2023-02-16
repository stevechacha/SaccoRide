package com.dev.chacha.presentation.auth.create_password

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CreatePasswordViewModel : ViewModel() {
    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _confirmPasswordText = mutableStateOf("")
    val confirmPasswordText: State<String> = _confirmPasswordText

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _showConfirmPassword = mutableStateOf(false)
    val showConfirmPassword: State<Boolean> = _showConfirmPassword


    private val _passwordError = mutableStateOf("")
    val passwordError: State<String> = _passwordError

    private val _confirmPasswordError = mutableStateOf("")
    val confirmPasswordError: State<String> = _confirmPasswordError

    fun setPasswordText(password: String) {
        _passwordText.value = password
    }

    fun setIsPasswordError(error: String) {
        _passwordError.value = error
    }

    fun setShowPassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }

    fun setShowConfirmPassword(showConfirmPassword: Boolean) {
        _showConfirmPassword.value = showConfirmPassword
    }
}
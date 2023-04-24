package com.dev.chacha.presentation.auth.create_password

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.chacha.presentation.auth.create_password.component.PasswordValidationState
import com.dev.chacha.presentation.auth.create_password.component.ValidatePassword
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class CreatePasswordViewModel(
    private val validatePassword: ValidatePassword = ValidatePassword()
) : ViewModel() {
    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    var password by mutableStateOf("")
        private set

    private val _confirmPasswordText = mutableStateOf("")
    val confirmPasswordText: State<String> = _confirmPasswordText

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _showConfirmPassword = mutableStateOf(false)
    val showConfirmPassword: State<Boolean> = _showConfirmPassword


   /* private val _passwordError = mutableStateOf("")
    val passwordError: State<String> = _passwordError*/

    private val _confirmPasswordError = mutableStateOf("")
    val confirmPasswordError: State<String> = _confirmPasswordError

    @OptIn(ExperimentalCoroutinesApi::class)
    val passwordError =
        snapshotFlow { password }
            .mapLatest { validatePassword.execute(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PasswordValidationState()
            )

    fun changePassword(value: String) {
        password = value
    }

    fun setPasswordText(password: String) {
        _passwordText.value = password
    }

   /* fun setIsPasswordError(error: String) {
        _passwordError.value = error
    }*/

    fun setShowPassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }

    fun setShowConfirmPassword(showConfirmPassword: Boolean) {
        _showConfirmPassword.value = showConfirmPassword
    }
}
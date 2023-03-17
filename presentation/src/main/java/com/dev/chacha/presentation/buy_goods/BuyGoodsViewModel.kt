package com.dev.chacha.presentation.buy_goods

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.chacha.presentation.auth.login.LoginState
import com.dev.chacha.presentation.auth.login.LoginUiEvents
import com.dev.chacha.presentation.auth.login.TextFieldState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class BuyGoodsViewModel : ViewModel(){
    private val _logOut = MutableLiveData<Boolean>()
    val logOut: LiveData<Boolean> = _logOut


    private val _usernameState = mutableStateOf(TextFieldState())
    val usernameState: State<TextFieldState> = _usernameState

    fun setUsername(value: String) {
        _usernameState.value = usernameState.value.copy(text = value)
    }

    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState
    fun setPassword(value: String) {
        _passwordState.value = _passwordState.value.copy(text = value)
    }

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword
    fun setShowPassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }


    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private val _eventFlow = MutableSharedFlow<LoginUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()


    /*private val _loginUIState = MutableLiveData<LoginUIState>(null)
    val loginUIState: LiveData<LoginUIState> = _loginUIState*/

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.value = loginState.value.copy(isLoading = true)

        }

        fun logout() {
            viewModelScope.launch(Dispatchers.IO) {
                logout()
            }
        }

    }
}
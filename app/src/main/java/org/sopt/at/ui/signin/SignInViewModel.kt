package org.sopt.at.ui.signin

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.at.data.AuthPreferences
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authPreferences: AuthPreferences
) : ViewModel() {

    private val _userId = MutableStateFlow(authPreferences.getUserId())
    val userId : StateFlow<String> = _userId.asStateFlow()

    private val _userPassword = MutableStateFlow(authPreferences.getUserPassword())
    val userPassword : StateFlow<String> = _userPassword.asStateFlow()

    private val _isVisiblePassword = MutableStateFlow(false)
    val isVisiblePassword = _isVisiblePassword.asStateFlow()

    fun updateId(id: String) {
        _userId.value = id
    }

    fun updatePassword(password : String) {
        _userPassword.value = password
    }

    fun isValidUser() : Boolean {
        return userId.value == authPreferences.getUserId() &&
                userPassword.value == authPreferences.getUserPassword()
    }

    fun login() {
        authPreferences.setLoggedIn(true)
    }




}


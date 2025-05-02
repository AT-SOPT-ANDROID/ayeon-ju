package org.sopt.at.ui.signup

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {


    private val _userId = MutableStateFlow("")
    val userId: StateFlow<String> = _userId.asStateFlow()

    private val _userPassword = MutableStateFlow("")
    val userPassword: StateFlow<String> = _userPassword.asStateFlow()

    private val _isPasswordVisible = MutableStateFlow(false)
    val isPasswordVisible = _isPasswordVisible.asStateFlow()

    private val _isIdScreen = MutableStateFlow(true)
    val isIdScreen = _isIdScreen.asStateFlow()


    companion object {
        private val idRegex = Regex("^[a-z0-9]{6,12}$")
        private val passwordRegex = Regex("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,12}$")
    }

    fun updateId(id : String) {
        _userId.value = id
    }

    fun updatePassword(password : String) {
        _userPassword.value = password
    }



    fun togglePasswordVisibility() {
        _isPasswordVisible.value = !_isPasswordVisible.value
    }

    fun validateId(): Boolean = _userId.value.matches(idRegex)

    fun validatePassword(): Boolean = _userPassword.value.matches(passwordRegex)

    fun onNextClick() {
        _isIdScreen.value = false

    }


}
package org.sopt.at.ui.signup

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    var id by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)
    var isIdScreen by mutableStateOf(true)

    companion object {
        private val idRegex = Regex("^[a-z0-9]{6,12}$")
        private val passwordRegex = Regex("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,12}$")
    }




    fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
    }

    fun validateId(): Boolean {
        return id.matches(idRegex)
    }

    fun validatePassword(): Boolean {
        return password.matches(passwordRegex)
    }

    fun onNextClick() {
        isIdScreen = false

    }


}
package org.sopt.at.ui.signin

import androidx.lifecycle.ViewModel
import org.sopt.at.data.AuthPreferences

class SignInViewModel(private val authPreferences: AuthPreferences) : ViewModel() {

    fun saveUserInfo(id: String, password: String) {
        authPreferences.saveAuthPreference(id, password)
    }

    fun getUserId(): String? = authPreferences.getUserId()

    fun getUserPassword(): String? = authPreferences.getUserPassword()

}


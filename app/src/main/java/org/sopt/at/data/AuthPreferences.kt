package org.sopt.at.data

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthPreferences @Inject constructor(
    @ApplicationContext private val context: Context) {

    private val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    fun setLoggedIn(isLoggedIn: Boolean) {

        prefs.edit() { putBoolean("isLoggedIn", isLoggedIn) }

    }

    fun isLoggedIn(): Boolean {

        return prefs.getBoolean("isLoggedIn", false)

    }



    fun saveAuthPreference(id: String, password: String) {
        prefs.edit() {
            putString("userId", id)
                .putString("userPassword", password)
        }
    }

    fun getUserId(): String {

        return prefs.getString("userId", "") ?:""

    }

    fun getUserPassword(): String {

        return prefs.getString("userPassword", "")?:""

    }


}
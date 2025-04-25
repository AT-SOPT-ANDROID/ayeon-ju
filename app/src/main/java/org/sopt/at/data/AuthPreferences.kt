package org.sopt.at.data

import android.content.Context
import androidx.core.content.edit

class AuthPreferences(context: Context) {

    private val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    fun setLoggedIn(isLoggedIn: Boolean) {

        prefs.edit() { putBoolean("isLoggedIn", isLoggedIn) }

    }

    fun isLoggedIn(): Boolean {

        return prefs.getBoolean("isLoggedIn", false)

    }

    fun clearLoggedIn() {

        prefs.edit() { clear() }

    }

    fun saveAuthPreference(id: String, password: String) {
        prefs.edit() {
            putString("userId", id)
                .putString("userPassword", password)
        }
    }

    fun getUserId(): String? = prefs.getString("userId", null)

    fun getUserPassword(): String? = prefs.getString("userPassword", null)


}
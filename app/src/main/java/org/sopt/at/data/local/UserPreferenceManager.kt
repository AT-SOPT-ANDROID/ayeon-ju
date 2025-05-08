package org.sopt.at.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_prefs")


@Singleton
class UserPreferenceManager @Inject constructor(
    @ApplicationContext private val  context: Context
) {

    companion object{
        val USER_ID = stringPreferencesKey("user_id")
        val USER_PASSWORD = stringPreferencesKey("user_password")
        val USER_NICKNAME = stringPreferencesKey("user_nickname")
    }

    suspend fun saveUserInfo(id: String, password: String, nickname: String) {
        context.dataStore.edit { prefs->
            prefs[USER_ID] = id
            prefs[USER_PASSWORD] = password
            prefs[USER_NICKNAME] = nickname
        }
    }

    fun getUserId(): Flow<String> {
        return context.dataStore.data.map { it[USER_ID] ?: ""}


    }

    fun getUserPassword(): Flow<String> {
        return context.dataStore.data.map { it[USER_PASSWORD] ?:"" }
    }

    fun getUserNickname(): Flow<String> {
        return context.dataStore.data.map { it[USER_NICKNAME] ?:"" }
    }

    fun isLoggedIn(): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->
            val id = prefs[USER_ID]
            val password = prefs[USER_PASSWORD]
            !id.isNullOrBlank() && !password.isNullOrBlank()
        }
    }


}
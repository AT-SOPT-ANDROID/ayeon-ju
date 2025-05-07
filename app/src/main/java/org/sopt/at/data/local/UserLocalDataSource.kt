package org.sopt.at.data.local

import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {


    suspend fun saveUserInfo(id: String, password: String)
    fun getUserId(): Flow<String>
    fun getUserPassWord(): Flow<String>

    suspend fun isLoggedIn(): Flow<Boolean>
}
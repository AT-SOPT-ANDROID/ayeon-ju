package org.sopt.at.data.local

import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {


    suspend fun saveUserInfo(id: String, password: String, nickname: String)
    fun getUserId(): Flow<String>
    fun getUserPassWord(): Flow<String>
    fun getUserNickname(): Flow<String>

    suspend fun isLoggedIn(): Flow<Boolean>
}
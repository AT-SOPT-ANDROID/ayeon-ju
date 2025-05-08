package org.sopt.at.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun saveUserInfo(id: String, password: String, nickname: String)
    fun getUserId(): Flow<String>
    fun getUserPassword(): Flow<String>
    fun getUserNickName(): Flow<String>

    suspend fun isLoggedIn(): Flow<Boolean>
}
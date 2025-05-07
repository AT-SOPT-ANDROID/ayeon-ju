package org.sopt.at.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun saveUserInfo(id: String, password: String)
    fun getUserId(): Flow<String>
    fun getUserPassword(): Flow<String>

    suspend fun isLoggedIn(): Flow<Boolean>
}
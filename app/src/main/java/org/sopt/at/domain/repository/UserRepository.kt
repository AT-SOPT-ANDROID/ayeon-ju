package org.sopt.at.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.dto.request.SignUpRequestDto
import org.sopt.at.data.dto.response.BaseResponse
import org.sopt.at.data.dto.response.SignUpResponse

interface UserRepository {

    suspend fun saveUserInfo(id: String, password: String, nickname: String)
    fun getUserId(): Flow<String>
    fun getUserPassword(): Flow<String>
    fun getUserNickName(): Flow<String>

    suspend fun isLoggedIn(): Flow<Boolean>

    suspend fun signUpUser(
        signUpRequestDto: SignUpRequestDto
    ): Result<SignUpResponse>

}
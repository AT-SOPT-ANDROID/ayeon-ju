package org.sopt.at.data.impl

import androidx.compose.runtime.saveable.autoSaver
import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.dto.request.SignUpRequestDto
import org.sopt.at.data.dto.response.SignUpResponse
import org.sopt.at.data.dto.service.AuthService
import org.sopt.at.data.local.UserLocalDataSource
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val authService: AuthService
) : UserRepository {



    override suspend fun saveUserInfo(id: String, password: String, nickname: String) {
        userLocalDataSource.saveUserInfo(id, password, nickname)
    }

    override fun getUserId(): Flow<String> {
        return userLocalDataSource.getUserId()
    }

    override fun getUserPassword(): Flow<String> {
        return userLocalDataSource.getUserPassWord()
    }

    override fun getUserNickName(): Flow<String> {
        return userLocalDataSource.getUserNickname()
    }


    override suspend fun isLoggedIn(): Flow<Boolean> {
        return userLocalDataSource.isLoggedIn()
    }

    override suspend fun signUpUser(signUpRequestDto: SignUpRequestDto): Result<SignUpResponse> {


        return try {

            val response = authService.signUpUser(signUpRequestDto)


            if (response.isSuccessful) {
                val body = response.body()!!
                if (body.success && body.data != null) {
                    Result.success(body.data)
                } else {
                    Result.failure((Exception(body.message)))
                }
            } else {
                Result.failure(Exception("서버 오류: ${response.code()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }


    }


}

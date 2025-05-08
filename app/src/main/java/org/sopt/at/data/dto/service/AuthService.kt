package org.sopt.at.data.dto.service

import org.sopt.at.data.dto.request.SignInRequestDto
import retrofit2.Response
import org.sopt.at.data.dto.request.SignUpRequestDto
import org.sopt.at.data.dto.response.BaseResponse
import org.sopt.at.data.dto.response.SignInResponse
import org.sopt.at.data.dto.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/api/v1/auth/signup")
    suspend fun signUpUser(
        @Body signUpRequestDto: SignUpRequestDto
    ): Response<BaseResponse<SignUpResponse>>


    @POST("/api/v1/auth/signin")
    suspend fun signInUser(
        @Body signInRequestDto: SignInRequestDto
    ): Response<BaseResponse<SignInResponse>>

}
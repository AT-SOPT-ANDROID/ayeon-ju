package org.sopt.at.data.impl

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.local.UserLocalDataSource
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {



    override suspend fun saveUserInfo(id: String, password: String) {
        userLocalDataSource.saveUserInfo(id, password)
    }

    override fun getUserId(): Flow<String> {
        return userLocalDataSource.getUserId()
    }

    override fun getUserPassword(): Flow<String> {
        return userLocalDataSource.getUserPassWord()
    }
}

package org.sopt.at.data.impl

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.local.UserLocalDataSource
import org.sopt.at.data.local.UserPreferenceManager
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userPreferenceManager: UserPreferenceManager
): UserLocalDataSource {

    override suspend fun saveUserInfo(id: String, password: String) {
        userPreferenceManager.saveUserInfo(id, password)

    }

    override fun getUserId(): Flow<String> {
        return userPreferenceManager.getUserId()
    }

    override fun getUserPassWord(): Flow<String> {
        return userPreferenceManager.getUserPassword()
    }
}
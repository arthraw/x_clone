package com.project.loginscreen.data.repository

import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.model.entities.UserEntity
import javax.inject.Inject

class UserDaoImpl @Inject constructor(
    private val dao: UserDao
): UserDao {
    override suspend fun createUser(user: UserEntity): UserEntity {
        return dao.createUser(user)
    }

    override suspend fun readUser(userId: Long): List<UserEntity> {
        return dao.readUser(userId)
    }

    override suspend fun updateUser(user: UserEntity): UserEntity {
        return dao.updateUser(user)
    }
}
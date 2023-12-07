package com.project.loginscreen.data.repository

import androidx.lifecycle.LiveData
import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.model.entities.UserEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDaoImpl @Inject constructor(
    private val dao: UserDao
): UserDao {
    override fun createUser(user: UserEntity) {
        return dao.createUser(user)
    }

    override fun readUser(userId: Long): List<UserEntity> {
        return dao.readUser(userId)
    }

    override fun updateUser(user: UserEntity): Int {
        return dao.updateUser(user)
    }

    override fun checkUser(name: String) : String {
        return dao.checkUser(name)
    }
}
package com.project.loginscreen.data.repository

import androidx.lifecycle.LiveData
import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.model.entities.UserEntity
import javax.inject.Inject

class UserDaoImpl @Inject constructor(
    private val dao: UserDao
): UserDao {
    override fun selectUsers(): List<UserEntity> {
        return dao.selectUsers()
    }

    override fun createUser(user: UserEntity) {
        return dao.createUser(user)
    }

    override fun readUser(id: Long): List<UserEntity> {
        return dao.readUser(id)
    }

    override fun updateUser(user: UserEntity): Int {
        return dao.updateUser(user)
    }

    override fun checkUser(name: String) : UserEntity? {
        return dao.checkUser(name)
    }

    override fun checkPass(name: String, password: String): UserEntity? {
        return dao.checkPass(name, password)
    }

    override fun checkEmail(email: String): UserEntity? {
        return dao.checkEmail(email)
    }
}
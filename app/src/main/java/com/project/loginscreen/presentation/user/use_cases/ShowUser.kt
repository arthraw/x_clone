package com.project.loginscreen.presentation.user.use_cases

import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.model.entities.UserEntity
import javax.inject.Inject

class ShowUser @Inject constructor(
    private val userDao: UserDao
) {
    suspend operator fun invoke(id: Long) : List<UserEntity> {
        return userDao.readUser(id)
    }
}
package com.project.loginscreen.presentation.user.use_cases

import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.model.entities.UserEntity
import javax.inject.Inject

class EditUser @Inject constructor(
    private val userDao: UserDao
) {

    suspend operator fun invoke(entity: UserEntity) {
        userDao.updateUser(entity)
    }
}
package com.project.loginscreen.presentation.user.use_cases

import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.model.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditUser @Inject constructor(
    private val userDao: UserDao
) {

    suspend operator fun invoke(entity: UserEntity) = withContext(Dispatchers.IO) {
        userDao.updateUser(entity)
    }
}
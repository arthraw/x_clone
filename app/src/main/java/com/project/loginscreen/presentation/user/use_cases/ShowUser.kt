package com.project.loginscreen.presentation.user.use_cases

import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.model.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowUser @Inject constructor(
    private val userDao: UserDao
) {
    suspend operator fun invoke(id: Long) : List<UserEntity> = withContext(Dispatchers.IO) {
        return@withContext userDao.readUser(id)
    }
}
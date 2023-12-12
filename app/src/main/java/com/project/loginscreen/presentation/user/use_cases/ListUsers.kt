package com.project.loginscreen.presentation.user.use_cases

import androidx.lifecycle.LiveData
import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.model.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListUsers @Inject constructor(
    private val dao: UserDao
) {
    suspend operator fun invoke() : List<UserEntity> = withContext(Dispatchers.IO) {
        return@withContext dao.selectUsers()
    }
}
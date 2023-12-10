package com.project.loginscreen.presentation.user.use_cases

import com.project.loginscreen.data.model.dao.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompareEmail @Inject constructor(
    private val dao: UserDao
) {
    suspend operator fun invoke(email: String) : String = withContext(Dispatchers.IO){
        return@withContext dao.checkEmail(email)
    }
}
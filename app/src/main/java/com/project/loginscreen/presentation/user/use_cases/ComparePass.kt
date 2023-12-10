package com.project.loginscreen.presentation.user.use_cases

import com.project.loginscreen.data.model.dao.UserDao
import javax.inject.Inject

class ComparePass @Inject constructor(
    private val dao: UserDao
) {
    suspend operator fun invoke(password: String): String {
        return dao.checkPass(password)
    }
}
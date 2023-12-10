package com.project.loginscreen.presentation.user.use_cases

import com.project.loginscreen.data.model.dao.UserDao
import javax.inject.Inject

class CompareEmail @Inject constructor(
    private val dao: UserDao
) {
    suspend operator fun invoke(email: String) : String {
        return dao.checkEmail(email)
    }
}
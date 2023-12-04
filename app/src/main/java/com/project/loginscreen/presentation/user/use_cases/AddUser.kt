package com.project.loginscreen.presentation.user.use_cases

import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.model.entities.InvalidUserException
import com.project.loginscreen.data.model.entities.UserEntity
import javax.inject.Inject
import kotlin.jvm.Throws

class AddUser @Inject constructor(
    private val userDao: UserDao
){
    @Throws(InvalidUserException::class)
    suspend operator fun invoke(entity: UserEntity) {
        if (entity.name.isBlank()) {
            throw InvalidUserException("The Username can't be blank.")
        }
        if (entity.email.isBlank()) {
            throw InvalidUserException("The E-mail can't be blank.")
        }
        if (entity.password.isBlank()) {
            throw InvalidUserException("The Password can't be blank.")
        }

        userDao.createUser(entity)
    }
}
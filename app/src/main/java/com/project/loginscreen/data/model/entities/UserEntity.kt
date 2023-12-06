package com.project.loginscreen.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class UserEntity(
    @PrimaryKey val userId : String = UUID.randomUUID().toString(),
    val name: String,
    val email : String,
    val password : String,
    val birthDate : Long?
)

class InvalidUserException(message: String): Exception(message)
package com.project.loginscreen.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class UserEntity(
    @ColumnInfo(name = "user_id") @PrimaryKey val userId : String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email : String,
    @ColumnInfo(name = "password") val password : String,
    @ColumnInfo(name = "birth_date") val birthDate : Long?
)

class InvalidUserException(message: String): Exception(message)
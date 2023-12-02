package com.project.loginscreen.data.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.model.entities.UserEntity

@Database(
    version = 1,
    entities = [UserEntity::class]
)
abstract class LoginScreenDataBase : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object {
        const val DATABASE_NAME = "login_screen_db"
    }
}
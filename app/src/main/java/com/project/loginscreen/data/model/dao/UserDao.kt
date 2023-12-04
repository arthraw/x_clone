package com.project.loginscreen.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.project.loginscreen.data.model.entities.UserEntity
import java.util.concurrent.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(user : UserEntity) : UserEntity
    @Query("SELECT * FROM UserEntity WHERE userId = :id")
    suspend fun readUser(id: Long) : List<UserEntity>
    @Update
    suspend fun updateUser(user : UserEntity) : UserEntity

}
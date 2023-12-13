package com.project.loginscreen.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapInfo
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.project.loginscreen.data.model.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity")
    fun selectUsers() : List<UserEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createUser(user : UserEntity)
    @Query("SELECT * FROM UserEntity WHERE user_id = :id" )
    fun readUser(id: Long) : List<UserEntity>
    @Update
    fun updateUser(user : UserEntity) : Int
    @Query("SELECT name FROM UserEntity WHERE name = :name")
    fun checkUser(name: String) : String?
    @MapInfo(keyColumn = "name", valueColumn = "password")
    @Query("SELECT name,password FROM UserEntity WHERE name = :name AND password = :password")
    fun checkPass(name: String, password: String) : LiveData<Map<String,String>>
    @Query("SELECT email FROM UserEntity WHERE email = :email")
    fun checkEmail(email: String) : String?

}
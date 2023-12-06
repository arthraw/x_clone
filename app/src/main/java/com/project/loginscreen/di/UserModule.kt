package com.project.loginscreen.di

import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.repository.UserDaoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

}
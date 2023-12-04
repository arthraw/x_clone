package com.project.loginscreen.di

import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.repository.UserDaoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class UserModule {
    @Binds
    abstract fun bindUserDao(
        userDaoImpl: UserDaoImpl
    ): UserDao
}
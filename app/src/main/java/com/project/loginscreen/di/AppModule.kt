package com.project.loginscreen.di

import android.app.Application
import androidx.room.Room
import com.project.loginscreen.data.model.LoginScreenDataBase
import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.repository.UserDaoImpl
import com.project.loginscreen.presentation.user.use_cases.AddUser
import com.project.loginscreen.presentation.user.use_cases.EditUser
import com.project.loginscreen.presentation.user.use_cases.ShowUser
import com.project.loginscreen.presentation.user.use_cases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataBase(app: Application) : LoginScreenDataBase {
        return Room.databaseBuilder(
            app,
            LoginScreenDataBase::class.java,
            LoginScreenDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db : LoginScreenDataBase) : UserDao {
        return UserDaoImpl(db.userDao())
    }

    @Provides
    @Singleton
    fun provideUseCases(dao: UserDao) : UserUseCases {
        return UserUseCases(
            addUser = AddUser(dao),
            editUser = EditUser(dao),
            showUser = ShowUser(dao)
        )
    }
}
package com.project.loginscreen.di

import android.app.Application
import androidx.room.Room
import com.project.loginscreen.data.model.LoginScreenDataBase
import com.project.loginscreen.data.model.dao.UserDao
import com.project.loginscreen.data.repository.UserDaoImpl
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
}
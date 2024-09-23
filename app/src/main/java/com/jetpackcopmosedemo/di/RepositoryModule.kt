package com.jetpackcopmosedemo.di

import com.jetpackcopmosedemo.data.datasource.ApiService
import com.jetpackcopmosedemo.data.repository.auth.AuthRepositoryImpl
import com.jetpackcopmosedemo.domain.repository.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService): AuthRepository {
        return AuthRepositoryImpl(apiService)
    }
}

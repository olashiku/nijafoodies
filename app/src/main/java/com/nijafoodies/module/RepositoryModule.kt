package com.nijafoodies.module

import com.nijafoodies.repository.RemoteService
import com.nijafoodies.repository.RemoteServiceImpl
import com.nijafoodies.socketRepository.SocketRepository
import com.nijafoodies.socketRepository.SocketRepositoryImpl
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
    fun providesRemoteService(): RemoteService = RemoteServiceImpl()

    @Provides
    @Singleton
    fun provideSocketService():SocketRepository = SocketRepositoryImpl()
}


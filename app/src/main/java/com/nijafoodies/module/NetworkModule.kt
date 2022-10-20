package com.nijafoodies.module

import com.nijafoodies.Base.BaseRepository
import com.nijafoodies.Base.BaseSocketRepository
import com.nijafoodies.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    @Provides
    @Singleton
    fun providesNetwork(): NetworkProvider = NetworkProvider()


    @Provides
    @Singleton
    fun providesBaseRepoWithNetwork(): BaseRepository = BaseRepository(networkProvider = NetworkProvider())


    @Provides
    @Singleton
    fun provideBaseSocketWithNetwork():BaseSocketRepository = BaseSocketRepository(networkProvider = NetworkProvider())
}


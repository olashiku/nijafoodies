package com.qucoon.module

import com.qucoon.Base.BaseRepository
import com.qucoon.network.NetworkProvider
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
    fun providesBaseRepoWithNetwork(networkProvider: NetworkProvider): BaseRepository = BaseRepository(networkProvider = NetworkProvider())
}

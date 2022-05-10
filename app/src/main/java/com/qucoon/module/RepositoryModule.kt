package com.qucoon.module

import com.qucoon.network.NetworkProvider
import com.qucoon.repository.RemoteService
import com.qucoon.repository.RemoteServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRemoteService(): RemoteService = RemoteServiceImpl()
}


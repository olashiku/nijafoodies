package com.qucoon.module

import com.qucoon.network.NetworkProvider
import com.qucoon.repository.RemoteService
import com.qucoon.repository.RemoteServiceImpl
import com.qucoon.viewmodel.PostViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Singleton
    @Provides
    fun providesViewModel(remoteService: RemoteService):PostViewModel= PostViewModel(remoteService = remoteService)
}



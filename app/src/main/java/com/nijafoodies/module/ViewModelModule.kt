package com.nijafoodies.module

import com.nijafoodies.repository.RemoteService
import com.nijafoodies.socketRepository.SocketRepository
import com.nijafoodies.viewmodel.networkvm.PostViewModel
import com.nijafoodies.viewmodel.socketvm.SocketViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Singleton
    @Provides
    fun providesViewModel(remoteService: RemoteService): PostViewModel = PostViewModel(remoteService = remoteService)


//    @Singleton
//    @Provides
//    fun providesAuthenticationViewModel(data:String) = AuthenticationViewModel(data = data)

     @Singleton
     @Provides
     fun providesSocketViewModel(socketRepository: SocketRepository): SocketViewModel = SocketViewModel(socketRepository = socketRepository)
}



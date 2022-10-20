package com.nijafoodies.socketRepository

import com.nijafoodies.Base.BaseSocketRepository
import com.nijafoodies.network.NetworkProvider
import com.nijafoodies.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SocketRepositoryImpl @Inject constructor() :SocketRepository, BaseSocketRepository(networkProvider = NetworkProvider()) {

    override suspend fun openSocket():NetworkResult<Unit> {
     return openSocketSession()
    }

    override suspend fun sendMessage(message: String) {
        sendSocketMessage(message)
    }

    override suspend fun observeRequest(): Flow<String> {
      return  observeSocketMessage()
    }

    override suspend fun observeRequestString(): String {
       return observeRequestString()
    }

    override suspend fun closeSession(){
       closeSocketSession()
   }

}
package com.qucoon.socketRepository

import com.qucoon.Base.BaseSocketRepository
import com.qucoon.network.NetworkProvider
import com.qucoon.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SocketRepositoryImpl @Inject constructor() :SocketRepository,
    BaseSocketRepository(networkProvider = NetworkProvider()) {

    override suspend fun openSocket():NetworkResult<Unit> {
     return openSocketSession()
    }

    override suspend fun sendMessage(message: String) {
        sendSocketMessage(message)
    }

    override suspend fun observeRequest(): Flow<String> {
      return  observeSocketMessage()
    }

   override suspend fun closeSession(){
       closeSocketSession()
   }

}
package com.nijafoodies.socketRepository

import com.nijafoodies.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SocketRepository {
    suspend fun openSocket(): NetworkResult<Unit>
    suspend fun sendMessage(message:String)
    suspend  fun observeRequest(): Flow<String>
    suspend  fun observeRequestString(): String
    suspend fun closeSession()
}
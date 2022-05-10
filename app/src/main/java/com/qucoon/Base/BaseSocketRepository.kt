package com.qucoon.Base

import com.qucoon.network.Message
import com.qucoon.network.NetworkProvider
import com.qucoon.network.NetworkResult
import com.qucoon.utils.Constants
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Exception

interface ChatSocketService{
    suspend fun <T:Any> openSocketSession(): NetworkResult<T>
    suspend fun <T:Any> sendSocketMessage(message:T)
    suspend fun closeSocketSession()
}

open class BaseSocketRepository(val networkProvider: NetworkProvider):ChatSocketService {
     var  socket : WebSocketSession?  = null

    override suspend  fun <T:Any> openSocketSession(): NetworkResult<T> {
        return try {
            if(socket!!.isActive){
                socket = networkProvider.socketClient.webSocketSession {
                    url(Constants.testwebSocketUrl)
                }
                NetworkResult.SuccessSocketConnection()
            }else {
                NetworkResult.Failed("Session has closed")
            }
        }catch (ex: Exception){
            NetworkResult.Errror(ex)
        }
    }

    override suspend fun <T:Any> sendSocketMessage(message: T) {
        try {
            socket?.send(Frame.Text(message.toString()))
        }catch (ex:Exception){
            ex.printStackTrace()
        }
    }

     suspend inline fun < reified T:Any> observeSocketMessage(): Flow<T> {
        return  try {
            socket!!.incoming.receiveAsFlow().filter { it is Frame.Text }.map {
                val json = (it as Frame.Text).readText()?: ""
                val message = Json.decodeFromString<T>(json)
                message
            }?: flow {  }
        }catch (ex:Exception){
            ex.printStackTrace()
            flow {  }
        }
    }
    override suspend fun closeSocketSession() {
        socket?.close()
    }

}
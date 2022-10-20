package com.nijafoodies.Base

import com.nijafoodies.network.NetworkProvider
import com.nijafoodies.network.NetworkResult
import com.nijafoodies.utils.Constants
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import javax.inject.Inject

interface ChatSocketService{
    suspend fun <T:Any> openSocketSession(): NetworkResult<T>
    suspend fun <T:Any> sendSocketMessage(message:T)
    suspend fun closeSocketSession()
}

open class BaseSocketRepository @Inject constructor(val networkProvider: NetworkProvider):ChatSocketService {
     var  socket : WebSocketSession?  = null

    override suspend  fun <T:Any> openSocketSession(): NetworkResult<T> {
        return try {
            if(socket?.isActive != true){
                socket = networkProvider.socketClient.webSocketSession {
                    url(Constants.webSocketUrl)
                }
                NetworkResult.SuccessSocketConnection("success")
            }else {
                socket = networkProvider.socketClient.webSocketSession {
                    url(Constants.webSocketUrl)
                }
                NetworkResult.Failed("network error, something happened")
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


     suspend fun observeMessageString(): String {
        var   response = ""
        try {
            for (message in socket!!.incoming) {
                message as? Frame.Text ?: continue
                response =  message.readText()
                println("serverMessage $response")
            }
        } catch (e: Exception) {
            response  = ("Error while receiving: " + e.localizedMessage)
        }
        return response
    }


       fun  observeSocketMessage(): Flow<String> {
        return  try {
            socket!!.incoming.receiveAsFlow().filter { it is Frame.Text }.map {
                val string = (it as Frame.Text).readText()?: ""
                string
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
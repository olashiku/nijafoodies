package com.qucoon.network

import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.qucoon.model.request.PostRequest
import com.qucoon.utils.Constants
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Exception

interface ChatSocketService{
    suspend  fun openSession():NetworkResult<Message>
    suspend fun  sendMessage(message:String)
    suspend fun observeMessage(): Flow<String>
    suspend fun observeMessageString(): String
    suspend fun closeSession()
}

class SocketProvider(val client: HttpClient):ChatSocketService {

    private var  socket : WebSocketSession?  = null

  override suspend  fun openSession():NetworkResult<Message>{

      return try {

          if(socket!!.isActive){
              openWebSocket()
              NetworkResult.Success(Message("successful"))
          }else {
              NetworkResult.Success(Message("successful"))

          }
      }catch (ex:Exception){
          NetworkResult.Errror(ex)
      }
 }

   suspend  fun openWebSocket(){
         socket = client.webSocketSession {
             url(Constants.testwebSocketUrl)
         }
     }

    override suspend fun sendMessage(message: String) {
        try {
            socket?.send(Frame.Text(message))
        }catch (ex:Exception){
            ex.printStackTrace()
        }
    }

    override suspend fun observeMessage(): Flow<String> {
      return  try {
            socket!!.incoming.receiveAsFlow().filter { it is Frame.Text }.map {
            val json = (it as Frame.Text).readText()?: ""
                val message = Json.decodeFromString<String>(json)
                message
            }?: flow {  }
    }catch (ex:Exception){
    ex.printStackTrace()
            flow {  }
    }
 }

    override suspend fun observeMessageString(): String {
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

    override suspend fun closeSession() {
      socket?.close()
    }


}

fun main() {
    val client =  HttpClient(CIO){
        install(WebSockets)
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    runBlocking {
        val opensocket = SocketProvider(client)
        val response = opensocket.openSession()
        when(response){
          is  NetworkResult.Success->{
              opensocket.sendMessage("Hello world")
            val message =  opensocket.observeMessageString()
              println("Message is $message")
            }
            is NetworkResult.Failed<*> ->{
                println(response.toString())
            }
            is   NetworkResult.Errror->{
                println(response.exception.localizedMessage)
            }else->{}
        }
    }
}


data class Message(
    var  message:String
)

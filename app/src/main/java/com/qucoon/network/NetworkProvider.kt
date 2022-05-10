package com.qucoon.network

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NetworkProvider @Inject constructor() {
    val client =  HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(JsonFeature){
                serializer = KotlinxSerializer(json)
            }


       defaultRequest {
          header("Content-Type", "application/json")

      }

       install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }
        }


    val socketClient = HttpClient(CIO) {
        install(WebSockets)
        install(Logging){
            level = LogLevel.BODY
        }
    }

    }

public val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = false
}

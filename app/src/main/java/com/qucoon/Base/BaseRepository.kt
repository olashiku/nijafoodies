package com.qucoon.Base

import com.qucoon.model.request.PostRequest
import com.qucoon.model.response.PostResponse
import com.qucoon.network.HttpRoutes
import com.qucoon.network.NetworkProvider
import com.qucoon.network.NetworkResult
import com.qucoon.utils.Constants
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.cio.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext


open class BaseRepository @Inject constructor( val networkProvider: NetworkProvider)  {

    suspend inline fun <R:Any,reified T:Any> executePostRequest(request: R,url:String,checkIfSuccessful:(T)->Boolean): NetworkResult<T> {
        return  try {
            val response: T = networkProvider.client.post {
                url(url)
                body = request
            }
            if(checkIfSuccessful(response)){
                NetworkResult.Success(response)
            }else {
                NetworkResult.Failed(response)
            }
        }catch (ex: Exception){
            NetworkResult.Errror(ex)
        }
    }

    suspend inline fun <reified T:Any> executeGetRequest(url:String,checkIfSuccessful:(T)->Boolean): NetworkResult<T> {
        return  try {
            val response: T = networkProvider.client.get {
                url(url)
            }
            if(checkIfSuccessful(response)){
                NetworkResult.Success(response)
            }else {
                NetworkResult.Failed(response)
            }
        }catch (ex: Exception){
            NetworkResult.Errror(ex)
        }
    }



}
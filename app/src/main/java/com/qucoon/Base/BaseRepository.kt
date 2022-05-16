package com.qucoon.Base

import com.qucoon.network.NetworkProvider
import com.qucoon.network.NetworkResult
import io.ktor.client.request.*
import javax.inject.Inject


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
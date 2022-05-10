package com.qucoon.network

sealed class NetworkResult<out T:Any> {
     class Success<out T:Any>(val data:T): NetworkResult<T>()
     class Errror(val exception: Throwable): NetworkResult<Nothing>()
    class Failed<out T:Any> ( message:T): NetworkResult<Nothing>()
    class SuccessSocketConnection<out T:Any>(): NetworkResult<T>()



}
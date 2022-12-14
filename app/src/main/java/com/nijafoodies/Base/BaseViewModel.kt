package com.nijafoodies.Base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijafoodies.network.NetworkResult
import com.nijafoodies.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


open class BaseViewModel: ViewModel() {
    val errorMessage = SingleLiveEvent<String>()
    val showLoading = MutableLiveData<Boolean>()

    fun <R:Any,T:Any> makePostRequest(request:R,response:SingleLiveEvent<T>, apiCall:suspend(request:R)-> NetworkResult<T>
    ,getError:(response:T) -> String){

        runBlocking {
            val result = withContext(Dispatchers.IO) {
                showLoading.postValue(true)
                apiCall(request)
            }
            showLoading.postValue(false)
            when(result){
                is NetworkResult.Success<*> ->{ response.postValue(result.data as T ) }
                is NetworkResult.Errror->{errorMessage.postValue(result.exception.localizedMessage)}
                is NetworkResult.Failed<*> ->{errorMessage.postValue(response.value?.let { getError(it) })}
                else -> {}
            }
        }
    }


    fun <T:Any> makeGetRequest(response:SingleLiveEvent<T>, apiCall:suspend ()-> NetworkResult<T>,
    getError:(response:T)->String){

        runBlocking {
            val result = withContext(Dispatchers.IO) {
                showLoading.postValue(true)
                apiCall()
            }
            showLoading.postValue(false)
            when(result){
                is NetworkResult.Success<*> ->{ response.postValue(result.data as T ) }
                is NetworkResult.Errror->{errorMessage.postValue(result.exception.localizedMessage)}
                is NetworkResult.Failed<*> ->{errorMessage.postValue(response.value?.let { getError(it)})}
                else -> {}
            }
        }
    }


}
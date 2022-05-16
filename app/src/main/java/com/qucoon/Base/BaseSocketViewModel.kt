package com.qucoon.Base

import androidx.lifecycle.*

import com.qucoon.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

open class BaseSocketViewModel: ViewModel() {

  inline fun <reified R:Any,S:Any> sendMessage(request:R, crossinline makeRequest:suspend(string:S)->Unit){
      val string = Json.encodeToString(request) as S
      viewModelScope.launch {
          makeRequest(string)
      }
  }

   fun openSocketConnection(openSocket:suspend()->NetworkResult<Unit>, networkObserver:MutableLiveData<String>){
             viewModelScope.launch {
                 val result =   openSocket()
                 when(result){
                     is NetworkResult.SuccessSocketConnection<*> -> {
                         networkObserver.postValue("Success")
                     }
                     is NetworkResult.Failed<*> ->{
                         networkObserver.postValue("Failed")
                     }
                     is NetworkResult.Errror -> {
                         networkObserver.postValue(result.exception.localizedMessage)
                     }
                     else -> {}
                 }
    }
  }

    fun  observeSessionResponse(observeRequest:suspend()-> Flow<String>): LiveData<String>? {
        var result : LiveData<String>? = null
        viewModelScope.launch {
            result = observeRequest().asLiveData()
        }
        return  result
    }
}
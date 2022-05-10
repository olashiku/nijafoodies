package com.qucoon.viewmodel

import android.accounts.NetworkErrorException
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.qucoon.Base.BaseSocketRepository
import com.qucoon.Base.BaseViewModel
import com.qucoon.model.request.authyRequest.AuthyRequest
import com.qucoon.network.NetworkResult
import com.qucoon.socketRepository.SocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


@HiltViewModel
class SocketViewModel @Inject constructor(val socketRepository: SocketRepository):BaseViewModel(){

    fun openConnection():Boolean{
        var isActive = false
        viewModelScope.launch {

      val result =   socketRepository.openSocket()
            isActive = when(result){
                is NetworkResult.SuccessSocketConnection -> {
                    true
                }
                is NetworkResult.Failed<*> ->{
                    false
                }
                is NetworkResult.Errror -> {
                    false
                }
                else ->{
                    false
                }
            }
        }
        return isActive

    }

     fun sendmessageToSocket(request: AuthyRequest){
         val jsonString = Json.encodeToString(request)
         viewModelScope.launch {
             socketRepository.sendMessage(jsonString)
         }
     }

     fun observeRequest(): Flow<String>? {
         var result : Flow<String>? = null
         viewModelScope.launch {
            result =  socketRepository.observeRequest()
             result?.asLiveData()
         }
         return  result

     }



}
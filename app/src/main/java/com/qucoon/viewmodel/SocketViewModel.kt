package com.qucoon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.qucoon.Base.BaseSocketViewModel
import com.qucoon.model.request.authyRequest.AuthyRequest
import com.qucoon.model.request.authyRequest.Msgsender
import com.qucoon.network.NetworkResult
import com.qucoon.socketRepository.SocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


@HiltViewModel
class SocketViewModel @Inject constructor(val socketRepository: SocketRepository): BaseSocketViewModel(){
    var isOpenLiveData = MutableLiveData<String>()

    fun openConnection(){
        openSocketConnection(socketRepository::openSocket,isOpenLiveData)
    }


     fun sendmessageToSocket(){
         val msgsender = Msgsender("MAC-1029383","11111111111","psk-87yaygufhbivs9f8hufne9suajer8jae8sgife9j8swrijef9sv8brhwje9fgswnu")
         val request = AuthyRequest("authy","123223","{lat:38.8951, log:-77.0364}",
             "Web",msgsender,"2020-06-30 T 10:45")
       sendMessage(request,socketRepository::sendMessage)
     }

     fun observeResponse(): LiveData<String>? {
         return observeSessionResponse(socketRepository::observeRequest)
     }
}
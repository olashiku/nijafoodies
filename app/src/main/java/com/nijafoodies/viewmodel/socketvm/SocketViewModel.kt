package com.nijafoodies.viewmodel.socketvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nijafoodies.Base.BaseSocketViewModel
import com.nijafoodies.model.socketData.request.authy.AuthyRequest
import com.nijafoodies.model.socketData.request.authy.Msgsender
import com.nijafoodies.model.socketData.response.friends.FriendsResponse
import com.nijafoodies.model.socketData.response.profile.ProfileResponse
import com.nijafoodies.socketRepository.SocketRepository
import com.nijafoodies.utils.decodeDataFromString
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONObject
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

     fun extractData(data:String){
         var obj = JSONObject(data)
         val objectType = obj["type"]
         when(objectType){
             "authy"->{AuthenticationViewModel(data)}
             "profile"->{performProfileProcedure(data)}
             "friends"->{performFriendsProcedure(data)}
             else ->{}
         }
     }

    private fun performProfileProcedure(data:String){
        val profileData =  data.decodeDataFromString<ProfileResponse>()
        println(profileData)
    }

    private fun performFriendsProcedure(data:String){
        val friendsData =  data.decodeDataFromString<FriendsResponse>()
        println(friendsData)
    }
}
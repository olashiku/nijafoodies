package com.nijafoodies.viewmodel.socketvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijafoodies.Base.BaseViewModel
import com.nijafoodies.model.socketData.response.authy.failedresponse.AuthyFailedResponse
import com.nijafoodies.model.socketData.response.authy.successresponse.AuthyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONObject
import javax.inject.Inject


class AuthenticationViewModel (data:String):ViewModel() {

    val authenticationData = MutableLiveData<AuthyResponse>()

    init {
        println("mydata $data")
        performAuthenticationProcedure(data)
    }

    private fun performAuthenticationProcedure(data:String){
        var obj = JSONObject(data)
        val status = obj["status"]
        when(status){
            "accepted"->{
                val authyData = Json.decodeFromString<AuthyResponse>(data)
                authenticationData.postValue(authyData)
                println(authyData)
            }
            "denied"->{
                val authyData = Json.decodeFromString<AuthyFailedResponse>(data)
                println(authyData)
            }
        }
    }


}
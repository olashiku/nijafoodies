package com.qucoon

import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.qucoon.model.request.PostRequest
import com.qucoon.network.NetworkResult
import com.qucoon.repository.RemoteService
import com.qucoon.viewmodel.PostViewModel
import com.qucoon.viewmodel.SocketViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val socketViewModel: SocketViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        socketViewModel.openConnection()
         socketViewModel.isOpenLiveData.observe(this, Observer {
         socketViewModel.sendmessageToSocket()
         observeRequest()
     })
    }

    fun waitAndMakeAnotherRequest(){
        Handler().postDelayed({
            socketViewModel.sendmessageToSocket()
        },10000)
    }

    fun observeRequest(){
      val result =  socketViewModel.observeResponse()
        result?.observe(this, Observer {
            println("mysocketdata $it")
        })
    }
}

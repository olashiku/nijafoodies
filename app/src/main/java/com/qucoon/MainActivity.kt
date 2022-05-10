package com.qucoon

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.qucoon.model.request.PostRequest
import com.qucoon.network.NetworkResult
import com.qucoon.repository.RemoteService
import com.qucoon.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postViewModel.makePost()

        postViewModel.postData.observe(this, Observer {
            println("expectedValue $it")
        })

        postViewModel.errorMessage.observe(this, Observer {
            println("expectedValue $it")

        })



    }
}

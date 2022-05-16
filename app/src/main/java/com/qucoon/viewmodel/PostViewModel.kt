package com.qucoon.viewmodel

import com.qucoon.Base.BaseViewModel
import com.qucoon.model.request.PostRequest
import com.qucoon.model.response.PostResponse
import com.qucoon.repository.RemoteService
import com.qucoon.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel  @Inject constructor (private val remoteService: RemoteService):BaseViewModel(){

    val postData = SingleLiveEvent<PostResponse>()
    val getPostData = SingleLiveEvent<List<PostResponse>>()

    fun makePost(){
        val request = PostRequest("helloworld","yoo",0)
        makePostRequest(request,postData,remoteService::createPost,{it.responseMessage})
    }

     fun getPost(){
         makeGetRequest(getPostData,remoteService::getPosts) { "something happened" }
     }
}
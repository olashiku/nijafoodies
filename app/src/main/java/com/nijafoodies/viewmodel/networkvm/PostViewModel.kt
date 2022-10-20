package com.nijafoodies.viewmodel.networkvm

import com.nijafoodies.Base.BaseViewModel
import com.nijafoodies.model.request.PostRequest
import com.nijafoodies.model.response.PostResponse
import com.nijafoodies.repository.RemoteService
import com.nijafoodies.utils.SingleLiveEvent
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
fun createSomething(){}

     fun getPost(){
         makeGetRequest(getPostData,remoteService::getPosts) { "something happened" }
     }


}
package com.nijafoodies.repository

import com.nijafoodies.network.NetworkResult
import com.nijafoodies.model.request.PostRequest
import com.nijafoodies.model.response.PostResponse

interface RemoteService {
    suspend fun getPosts(): NetworkResult<List<PostResponse>>
    suspend fun createPost(postRequest: PostRequest): NetworkResult<PostResponse>
    suspend fun getPaymentItemList(){}
}

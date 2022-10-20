package com.nijafoodies.repository

import com.nijafoodies.Base.BaseRepository
import com.nijafoodies.model.request.PostRequest
import com.nijafoodies.model.response.PostResponse
import com.nijafoodies.network.HttpRoutes
import com.nijafoodies.network.NetworkProvider
import com.nijafoodies.network.NetworkResult
import javax.inject.Inject


class RemoteServiceImpl @Inject constructor(): RemoteService,BaseRepository(networkProvider = NetworkProvider()){

    override suspend fun getPosts(): NetworkResult<List<PostResponse>> {
        return  executeGetRequest(HttpRoutes.POSTS) { it.isNotEmpty() }
    }

    override suspend fun createPost(postRequest: PostRequest): NetworkResult<PostResponse> {
        return  executePostRequest(postRequest,HttpRoutes.POSTS) {it.id > 0}
    }


}
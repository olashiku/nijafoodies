package com.qucoon.repository

import com.qucoon.Base.BaseRepository
import com.qucoon.model.request.PostRequest
import com.qucoon.model.response.PostResponse
import com.qucoon.network.HttpRoutes
import com.qucoon.network.NetworkProvider
import com.qucoon.network.NetworkResult
import javax.inject.Inject


class RemoteServiceImpl @Inject constructor(): RemoteService,BaseRepository(networkProvider = NetworkProvider()){

    override suspend fun getPosts(): NetworkResult<List<PostResponse>> {
        return  executeGetRequest(HttpRoutes.POSTS) { it.isNotEmpty() }
    }

    override suspend fun createPost(postRequest: PostRequest): NetworkResult<PostResponse> {
        return  executePostRequest(postRequest,HttpRoutes.POSTS) {it.id > 0}
    }
}
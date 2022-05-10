package com.qucoon.repository

import com.qucoon.Base.BaseRepository
import com.qucoon.model.request.PostRequest
import com.qucoon.model.response.PostResponse
import com.qucoon.network.HttpRoutes
import com.qucoon.network.NetworkProvider
import com.qucoon.network.NetworkResult
import com.qucoon.network.SocketProvider
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import javax.inject.Inject


class RemoteServiceImpl @Inject constructor(): RemoteService,BaseRepository(networkProvider = NetworkProvider()){

    override suspend fun getPosts(): NetworkResult<List<PostResponse>> {
        return  executeGetRequest(HttpRoutes.POSTS) { it.isNotEmpty() }
    }

    override suspend fun createPost(postRequest: PostRequest): NetworkResult<PostResponse> {
        return  executePostRequest(postRequest,HttpRoutes.POSTS) {it.id > 0}
    }
}
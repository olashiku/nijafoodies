package com.qucoon.repository

import com.qucoon.network.NetworkResult
import com.qucoon.model.request.PostRequest
import com.qucoon.model.response.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface RemoteService {
    suspend fun getPosts(): NetworkResult<List<PostResponse>>
    suspend fun createPost(postRequest: PostRequest): NetworkResult<PostResponse>
}

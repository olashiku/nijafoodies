package com.nijafoodies.model.request

import kotlinx.serialization.*

@Serializable
data class PostRequest(
    val body: String,
    val title: String,
    val userId: Int
)
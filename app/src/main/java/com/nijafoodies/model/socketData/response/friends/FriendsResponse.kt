package com.nijafoodies.model.socketData.response.friends

import kotlinx.serialization.Serializable

@Serializable
data class FriendsResponse(
    val `data`: List<String>,
    val status: String,
    val type: String
)
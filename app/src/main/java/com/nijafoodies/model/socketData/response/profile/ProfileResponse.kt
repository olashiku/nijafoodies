package com.nijafoodies.model.socketData.response.profile

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val `data`: Data,
    val status: String,
    val type: String
)
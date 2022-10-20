package com.nijafoodies.model.socketData.response.authy.successresponse

import kotlinx.serialization.Serializable

@Serializable
data class AuthyResponse(
    val `data`: Data,
    val status: String,
    val type: String
)
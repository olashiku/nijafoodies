package com.nijafoodies.model.socketData.response.authy.failedresponse

import kotlinx.serialization.Serializable

@Serializable
data class AuthyFailedResponse(
    val `data`: List<Data>,
    val status: String,
    val type: String
)
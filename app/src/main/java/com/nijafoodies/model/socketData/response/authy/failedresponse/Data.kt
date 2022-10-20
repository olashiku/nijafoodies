package com.nijafoodies.model.socketData.response.authy.failedresponse

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val businessid: String,
    val msgsenderid: String,
    val sessionid: String
)
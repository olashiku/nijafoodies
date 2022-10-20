package com.nijafoodies.model.socketData.request.authy

import kotlinx.serialization.Serializable

@Serializable
data class AuthyRequest(
    val action: String,
    val msgid: String,
    val msglocation: String,
    val msgplatform: String,
    val msgsender: Msgsender,
    val msgtimestamp: String
)
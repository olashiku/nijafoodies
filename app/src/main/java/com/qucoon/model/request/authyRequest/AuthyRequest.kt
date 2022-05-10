package com.qucoon.model.request.authyRequest

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
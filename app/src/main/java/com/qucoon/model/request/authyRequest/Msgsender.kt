package com.qucoon.model.request.authyRequest

import kotlinx.serialization.Serializable

@Serializable
data class Msgsender(
    val msgsenderdevice: String,
    val msgsenderid: String,
    val psk: String
)
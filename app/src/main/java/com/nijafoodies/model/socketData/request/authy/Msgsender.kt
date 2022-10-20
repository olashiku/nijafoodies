package com.nijafoodies.model.socketData.request.authy

import kotlinx.serialization.Serializable

@Serializable
data class Msgsender(
    val msgsenderdevice: String,
    val msgsenderid: String,
    val psk: String
)
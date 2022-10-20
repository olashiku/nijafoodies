package com.nijafoodies.model.response
import kotlinx.serialization.Serializable


@Serializable
data class PostResponse(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    val responseCode:String = "",
    val responseMessage:String = ""
)
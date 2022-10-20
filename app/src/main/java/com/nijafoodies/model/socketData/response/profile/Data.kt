package com.nijafoodies.model.socketData.response.profile

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val businessid: String,
    val language: String,
    val lastmessage: String,
    val location: String,
    val online: String,
    val phone: String,
    val profile_type: String,
    val profiledm: String,
    val profileemail: String,
    val profileimage: String,
    val profilename: String,
    val profilestatus: String,
    val sessionid: String,
    val source: String,
    val userid: String
)
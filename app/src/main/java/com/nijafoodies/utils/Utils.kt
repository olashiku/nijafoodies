package com.nijafoodies.utils

import android.os.Handler
import android.os.Looper
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

inline fun < reified T:Any> String.decodeDataFromString():T{
    return Json.decodeFromString<T>(this)
}


 fun delayforSomeSeconds(dosomething:()->Unit,timer:Long = 4000){
     Handler(Looper.getMainLooper()).postDelayed({
         dosomething()
     }, timer)
 }
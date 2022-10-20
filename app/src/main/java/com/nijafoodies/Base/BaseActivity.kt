package com.nijafoodies.Base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

open class BaseActivity:AppCompatActivity() {


     fun setupObserver(observer:BaseViewModel){
         when(observer){
             observer.showLoading->{showloadingObserver(observer)}
             observer.errorMessage->{shoeErrorObserver(observer)}
         }
     }

    fun showloadingObserver(observer: BaseViewModel){
        observer.showLoading.observe(this, Observer {
        })
    }

    fun shoeErrorObserver(observer: BaseViewModel){
        observer.errorMessage.observe(this, Observer {
        })
    }


}
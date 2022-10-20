package com.nijafoodies.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.nijafoodies.Base.BaseActivity
import com.nijafoodies.R
import com.nijafoodies.databinding.ActivityMainBinding
import com.nijafoodies.viewmodel.networkvm.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding
    val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        postViewModel.makePost()
    }


    fun initView(){
        supportActionBar!!.hide()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
         navHostFragment.navController
    }
}




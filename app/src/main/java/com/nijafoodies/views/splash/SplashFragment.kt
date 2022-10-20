package com.nijafoodies.views.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nijafoodies.R
import com.nijafoodies.databinding.FragmentSplashBinding
import com.nijafoodies.utils.delayforSomeSeconds


class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delayforSomeSeconds({

            lifecycleScope.launchWhenResumed {
               findNavController().navigate(R.id.action_splashFragment_to_splashScreenFragment)
            }
        })
    }
}
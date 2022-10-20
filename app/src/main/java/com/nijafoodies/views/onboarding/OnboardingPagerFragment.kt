package com.nijafoodies.views.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nijafoodies.R
import com.nijafoodies.databinding.FragmentOnboardingBinding
import com.nijafoodies.databinding.FragmentOnboardingPagerBinding
import com.nijafoodies.model.dataHolder.ViewPagerDataClass
import java.io.Serializable

private const val ARG_PARAM1 = "param1"

class OnboardingPagerFragment : Fragment() {

    lateinit var binding : FragmentOnboardingPagerBinding
    private var viewPagerDataClass: ViewPagerDataClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewPagerDataClass = it.getSerializable(ARG_PARAM1) as ViewPagerDataClass
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingPagerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

     fun initView(){
         viewPagerDataClass?.let {
             binding.imageView.setImageResource(it.image)
             binding.textViewTitle.text = it.title1
             binding.textViewTitle2.text= it.title2
         }
     }


    companion object {
        @JvmStatic
        fun newInstance(param1: ViewPagerDataClass) =
            OnboardingPagerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}
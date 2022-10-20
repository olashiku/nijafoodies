package com.nijafoodies.views.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.nijafoodies.R
import com.nijafoodies.databinding.FragmentOnboardingBinding
import com.nijafoodies.keystonemobilebankingapp.utils.ViewPagerObject
import com.nijafoodies.keystonemobilebankingapp.utils.invisible
import com.nijafoodies.keystonemobilebankingapp.utils.setUpViewPager
import com.nijafoodies.keystonemobilebankingapp.utils.visible
import com.nijafoodies.model.dataHolder.ViewPagerDataClass

class OnboardingScreenFragment : Fragment() {
    var viewPagerCurrentPage = 0
    lateinit var binding:FragmentOnboardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupClickListner()
    }

     fun initView(){
     binding.tabLayout.setupWithViewPager(binding.viewPager)
     val list = listOf(
     ViewPagerDataClass(R.drawable.food_slide1,resources.getString(R.string.page1Title1),resources.getString(R.string.page1Title2),resources.getString(R.string.page1Body)),
     ViewPagerDataClass(R.drawable.food_slide_2,resources.getString(R.string.page2Title1),resources.getString(R.string.page2Title2),resources.getString(R.string.page2Body)),
     ViewPagerDataClass(R.drawable.food_slide_3,resources.getString(R.string.page3Title1),resources.getString(R.string.page3Title2),resources.getString(R.string.page3Body)))

     val viewPagerObj = list.map { ViewPagerObject(OnboardingPagerFragment.newInstance(it),"") }

     binding.viewPager.setUpViewPager(viewPagerObj,fragmentStateManager = childFragmentManager)
     binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
          override fun onPageScrollStateChanged(state: Int) {}
          override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
          override fun onPageSelected(position: Int) {
              viewPagerCurrentPage = position
              updateView(position)
          } })
     }

    fun updateView(position: Int){
        when(position){
            2->{ binding.button2.invisible()
                binding.onboardingFabButton.setImageResource(R.drawable.backarrow)
            }
            else->{ binding.button2.visible()
                binding.onboardingFabButton.setImageResource(R.drawable.backarrow)
            }
        }
    }

     fun setupClickListner(){
         binding.onboardingFabButton.setOnClickListener {
             if(viewPagerCurrentPage != 2){
                 binding.viewPager.currentItem = viewPagerCurrentPage+1
             }else{
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
             }
         }

        binding.button2.setOnClickListener{
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
        }
     }





}
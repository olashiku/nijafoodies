package com.qucoon.keystonemobilebankingapp.utils

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.qucoon.utils.RecyclerAdapterUtil
import com.qucoon.utils.ViewPagerUtils

data class ViewPagerObject(val fragment: Fragment, val title:String)

fun <T:Any> RecyclerView.updateRecycler(context: Context, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition:(Int) -> Unit,noImageViews:List<View>): RecyclerView.Adapter<*>? {
    if(listOfItems.isNullOrEmpty()){
        this.visibility = View.GONE
        noImageViews.forEach { it.visibility = View.VISIBLE }
    }
    this.layoutManager = LinearLayoutManager(context)
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }
    this.adapter = reyclerAdaptor
    return adapter
}

fun <T : Any> RecyclerView.updateRecycler(
    context: Context, listOfItems: List<T>, layout: Int,
    listOfLayout: List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition: (Int) -> Unit): RecyclerView.Adapter<*>? {
    this.layoutManager = LinearLayoutManager(context)
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context, listOfItems, layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{ itemView, item, position, innerViews ->
        binder(innerViews, position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }
    reyclerAdaptor.notifyDataSetChanged()
    this.adapter = reyclerAdaptor
    return adapter
}



fun ViewPager.setUpViewPager(viewPagerObjectList:List<ViewPagerObject>, fragmentStateManager: FragmentManager){
    val pagerAdapter = ViewPagerUtils.MyViewPageStateAdapter(fragmentStateManager)
    viewPagerObjectList.forEach {
        pagerAdapter.addFragment(it.fragment,it.title)
    }
    this.apply {
        adapter = pagerAdapter
    }
}

fun ViewPager.setUpViewPager(viewPagerObjectList:List<ViewPagerObject>, fragmentStateManager: FragmentManager, transform: ViewPager.PageTransformer){
    val pagerAdapter = ViewPagerUtils.MyViewPageStateAdapter(fragmentStateManager)
    viewPagerObjectList.forEach {
        pagerAdapter.addFragment(it.fragment,it.title)
    }
    this.apply {
        adapter = pagerAdapter
        setPageTransformer(true,transform)
    }
}

fun ViewPager.setUpViewPager(viewPagerObjectList:List<ViewPagerObject>, transform: ViewPager.PageTransformer, onPageChangeListener: ViewPager.OnPageChangeListener, fragmentStateManager: FragmentManager){
    val pagerAdapter = ViewPagerUtils.MyViewPageStateAdapter(fragmentStateManager)
    viewPagerObjectList.forEach {
        pagerAdapter.addFragment(it.fragment,it.title)
    }
    this.apply {
        adapter = pagerAdapter
        setPageTransformer(true,transform)
        addOnPageChangeListener(onPageChangeListener)
    }
}



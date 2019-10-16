package com.android.sportnmedc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

/**
 * Created by AndroidEB on 9/11/2017.
 */
abstract class BaseFragment<T : ViewModel>(@LayoutRes val layoutId:Int): Fragment(){

    val mViewModel by lazy {
        initViewModel()
    }
    abstract fun initViewModel():T
    abstract fun startView(view: View)

    lateinit var mActivity:AppCompatActivity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(layoutId,container,false)
        mActivity = activity as AppCompatActivity
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startView(view)
    }



}
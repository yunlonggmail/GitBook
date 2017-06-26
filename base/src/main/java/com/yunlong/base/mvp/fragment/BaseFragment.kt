package com.yunlong.base.mvp.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.find


/**
 * Created by shiyunlong on 2017/6/23.
 * Fragment基类
 */
abstract class BaseFragment(private val mResourceID: Int) : Fragment() {
    /**
     * 填充器
     */
    var mLayoutInflater: LayoutInflater? = null
    /**
     *基类View
     */
    var mRootView: View? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mLayoutInflater = inflater;
        mRootView = mLayoutInflater?.inflate(mResourceID, container, false);
        viewDidLoad()
        return mRootView
    }

    /**
     * View加载
     */
    abstract fun viewDidLoad()

    /**
     * findViewById简写
     */
    protected fun find(id: Int): View? {
        return mRootView?.find<View>(id)
    }

}
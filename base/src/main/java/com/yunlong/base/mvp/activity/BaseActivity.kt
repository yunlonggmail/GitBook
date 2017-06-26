package com.yunlong.base.mvp.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by shiyunlong on 2017/6/23.
 * Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {
    /**
     * 上下文
     */
    open var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(getLayoutId())
        activityInit()
    }


    abstract fun getLayoutId(): Int

    fun activityInit() {
        initTitleBar()
        initView()
        initData()
    }

    /**
     * 初始化TitleBar
     */
    abstract fun initTitleBar()

    /**
     * 初始化View
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()
}
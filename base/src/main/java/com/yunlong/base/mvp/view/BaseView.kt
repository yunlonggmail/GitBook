package com.yunlong.base.mvp.view

import android.content.Context

/**
 * Created by shiyunlong on 2017/6/23.
 * BaseView,MVP：V
 */
interface BaseView<T> {
    /**
     * 设置主持人
     */
    fun setPresenter(presenter: T)

    /**
     * 获取Context
     */
    fun getContext(): Context
}
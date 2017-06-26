package com.yunlong.gitbook

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

/**
 * Created by shiyunlong on 2017/6/23.
 * 默认的App
 */
class GitBookApp : Application() {
    /**
     * 拆分Dex文件
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    /**
     * 创建
     */
    override fun onCreate() {
        super.onCreate()
        initAccessToken()

    }

    /**
     * 初始化Token
     */
    fun initAccessToken(){

    }
}
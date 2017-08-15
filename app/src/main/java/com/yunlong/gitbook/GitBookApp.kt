package com.yunlong.gitbook

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.yunlong.base.util.DisplayUtils
import com.yunlong.gitbook.api.ServiceGenerator
import com.yunlong.gitbook.auth.manager.GitBookAccountManager

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
        ServiceGenerator.init(this)
        DisplayUtils.init(this)
    }

    /**
     * 初始化Token
     */
    fun initAccessToken() {
        GitBookAccountManager.initAccessToken(this)
    }
}
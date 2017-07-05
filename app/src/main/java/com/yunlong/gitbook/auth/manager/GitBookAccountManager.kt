package com.yunlong.gitbook.auth.manager

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.yunlong.base.util.SPUtils
import com.yunlong.base.util.StringUtils
import com.yunlong.gitbook.auth.model.AccessToken
import com.yunlong.gitbook.user.model.User
import java.lang.reflect.Type

/**
 * Created by shiyunlong on 2017/6/27.
 * 账号管理器
 */
object GitBookAccountManager {

    /**
     * Token
     */
    var mAccessToken: AccessToken? = null
    /**
     * 用户信息
     */
    var mAccount: User? = null

    /**
     * 得到Token
     */
    fun getAccessToken(): AccessToken? {
        return mAccessToken
    }

    /**
     * 设置AccessToken
     */
    fun setAccessToken(accessToken: AccessToken?) {
        this.mAccessToken = accessToken
    }

    /**
     * 是否登录
     */
    fun isLogin(): Boolean {
        return mAccessToken != null
    }

    fun initAccessToken(context: Context) {
        val accessTokenJson: String = SPUtils.getDefaultStrPre(context.applicationContext, AccountConfig.ACCESS_TOKEN_KEY, "")
        if (!StringUtils.isEmpty(accessTokenJson)) {
            val accessToken: AccessToken = Gson().fromJson(accessTokenJson, AccessToken::class.java)
            setAccessToken(accessToken)
        }
    }

    /**
     * 保存AccessToken
     */
    fun saveAccessToken(context: Context, accessToken: AccessToken?) {
        setAccessToken(accessToken)
        val accessTokenJson: String = Gson().toJson(accessToken)
        SPUtils.saveDefaultStrPre(context.applicationContext, AccountConfig.ACCESS_TOKEN_KEY, accessTokenJson)
    }

    /**
     * 获取账户信息
     */
    fun getAccountInfo(): User? {
        return mAccount
    }

    /**
     * 设置AccountInfo
     */
    fun setAccountInfo(account: User?) {
        this.mAccount = account
    }

}

object AccountConfig {
    /**
     * AccessToken_Key
     */
    val ACCESS_TOKEN_KEY: String = "access_token_key";
}
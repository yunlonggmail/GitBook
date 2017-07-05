package com.yunlong.gitbook.user.api

import com.yunlong.gitbook.api.ServiceGenerator
import com.yunlong.gitbook.auth.manager.GitBookAccountManager
import com.yunlong.gitbook.user.model.User
import io.reactivex.Observable
import retrofit2.Response
import java.util.*

/**
 * Created by shiyunlong on 2017/7/5.
 * 用户Repository
 */
class UserRepository {
    /**
     * 用户服务
     */
    var mUserService: UserService? = null


    init {
        mUserService = ServiceGenerator.createService(UserService::class.java, GitBookAccountManager.getAccessToken()) as UserService
    }

    /**
     * 获取账号信息
     */
    fun getAccountInfo(): Observable<Response<User>>? {
        return mUserService?.getAccountInfo()
    }

    /**
     * 获取作者信息
     */
    fun getAuthorInfo(username: String): Observable<Response<User>>? {
        return mUserService?.getAuthorInfo(username)
    }

}
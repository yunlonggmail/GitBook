package com.yunlong.gitbook.auth.api

import com.yunlong.gitbook.api.ApiConstants
import com.yunlong.gitbook.api.ServiceGenerator
import com.yunlong.gitbook.auth.manager.GitBookAccountManager
import com.yunlong.gitbook.auth.model.AccessToken
import com.yunlong.gitbook.auth.view.AuthConfig
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by shiyunlong on 2017/6/27.
 * 验证储藏室
 */
class AuthRepository {
    /**
     * AccessToken服务
     */
    var mAccessTokenService: AccessTokenService? = null

    init {
        mAccessTokenService = ServiceGenerator.createService(AccessTokenService::class.java, GitBookAccountManager.getAccessToken()) as AccessTokenService
    }

    /**
     * 获取AccessToken
     */
    fun getAccessToken(code: String?): Observable<Response<AccessToken>>? {
        return mAccessTokenService?.getAccessToken(ApiConstants.GIT_BOOK_CLIENT_ID,
                ApiConstants.GIT_BOOK_CLIENT_SECRET,
                code,
                AuthConfig.GRANT_TYPE_VALUE)
    }

}
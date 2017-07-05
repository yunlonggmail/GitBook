package com.yunlong.gitbook.auth.api

import com.yunlong.gitbook.api.ApiConstants
import com.yunlong.gitbook.auth.model.AccessToken
import com.yunlong.gitbook.auth.view.AuthConfig
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * Created by shiyunlong on 2017/6/27.
 * 验证服务
 */
interface AccessTokenService {

    @POST(ApiConstants.POST_GIT_BOOK_AUTH_ACCESS_TOKEN)
    @FormUrlEncoded
    fun getAccessToken(@Field(AuthConfig.CLIENT_ID_KEY) clientId: String,
                       @Field(AuthConfig.CLIENT_SECRET_KEY) clientSecret: String,
                       @Field(AuthConfig.RESPONSE_TYPE_CODE_KEY) code: String?,
                       @Field(AuthConfig.GRANT_TYPE_KEY) grandType: String): Observable<Response<AccessToken>>
}
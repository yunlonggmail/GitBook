package com.yunlong.gitbook.user.api

import com.yunlong.gitbook.api.ApiConstants
import com.yunlong.gitbook.auth.model.AccessToken
import com.yunlong.gitbook.user.model.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * Created by shiyunlong on 2017/6/28.
 * 作者的服务
 */
interface UserService {
    /**
     * 获取AccountInfo
     */
    @GET(ApiConstants.GET_GIT_BOOK_ACCOUNT_INFO)
    fun getAccountInfo(): Observable<Response<User>>

    /**
     * 获取Author信息
     */
    @GET(ApiConstants.GET_GIT_BOOK_AUTHOR_INFO)
    fun getAuthorInfo(@Path("username") username: String): Observable<Response<User>>

}
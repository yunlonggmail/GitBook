package com.yunlong.gitbook.auth

import android.net.Uri
import com.yunlong.base.mvp.activity.BaseWebActivity
import com.yunlong.base.mvp.http.HttpRequestParams
import com.yunlong.base.util.ToastUtils
import com.yunlong.gitbook.R
import com.yunlong.gitbook.api.ApiConstants

/**
 * Created by shiyunlong on 2017/6/26.
 * 验证界面
 */
class AuthActivity : BaseWebActivity() {
    /**
     * 验证Url
     */
    var mAuthUrl: String = ""

    override fun processData() {
        mNeedProcessUrl = true
        initAuthUrl()
        initTitle()
    }

    /**
     * 初始化Uri
     */
    private fun initAuthUrl() {
        val httpRequestParams: HttpRequestParams = HttpRequestParams()
        httpRequestParams.addParameter(AuthConfig.CLIENT_ID_KEY, ApiConstants.GIT_BOOK_CLIENT_ID)
        httpRequestParams.addParameter(AuthConfig.REDIRECT_URI_KEY, ApiConstants.GIT_BOOK_AUTH_AUTHORIZE_REDIRECT_URI)
        httpRequestParams.addParameter(AuthConfig.RESPONSE_TYPE_KEY, AuthConfig.RESPONSE_TYPE_CODE_KEY)
        mAuthUrl = ApiConstants.GET_GIT_BOOK_AUTH_AUTHORIZE + httpRequestParams.getString()

        loadUrl(mWebView, mAuthUrl)
    }

    /**
     * 初始化Title
     */
    fun initTitle() {
        mTitle = getString(R.string.a_auth_authorize)
        setTitleBar(mTitle)
    }

    /**
     * 处理其他Uri
     */
    override fun processOtherUri(uri: Uri?) {
        if (isAuthAuthorizeRedirectUri(uri)) {
            val code: String? = uri?.getQueryParameter(AuthConfig.RESPONSE_TYPE_CODE_KEY)
            code?.let {
                ToastUtils.show(mContext, code)
                finish()
            }
        } else {
            loadUrl(mWebView, uri.toString())
        }
    }

    /**
     * 是否是反馈URI
     */
    fun isAuthAuthorizeRedirectUri(uri: Uri?): Boolean {
        val redirectUri: Uri = Uri.parse(ApiConstants.GIT_BOOK_AUTH_AUTHORIZE_REDIRECT_URI)
        uri?.let {
            return uri.scheme == redirectUri.scheme && uri.host == redirectUri.host && uri.path == redirectUri.path
        } ?: return false
    }
}

class AuthConfig {
    companion object {
        /**
         * Client_ID的Key
         */
        val CLIENT_ID_KEY: String = "client_id"
        /**
         * 回调URI的KEY
         */
        val REDIRECT_URI_KEY: String = "redirect_uri"
        /**
         * 回调类型
         */
        val RESPONSE_TYPE_KEY: String = "response_type"
        /**
         * 返回码
         */
        val RESPONSE_TYPE_CODE_KEY: String = "code"
    }
}
package com.yunlong.gitbook.auth.view

import android.app.Activity
import android.content.Context
import android.net.Uri
import com.yunlong.base.mvp.activity.BaseWebActivity
import com.yunlong.base.mvp.http.HttpRequestParams
import com.yunlong.base.util.ToastUtils
import com.yunlong.gitbook.R
import com.yunlong.gitbook.api.ApiConstants
import com.yunlong.gitbook.auth.contract.AuthContract
import com.yunlong.gitbook.auth.model.AccessToken
import com.yunlong.gitbook.auth.presenter.AuthPresenter

/**
 * Created by shiyunlong on 2017/6/26.
 * 验证界面
 */
class AuthActivity : BaseWebActivity(), AuthContract.View {
    /**
     * 验证Url
     */
    var mAuthUrl: String = ""
    /**
     * 主持人
     */
    var mPresenter: AuthContract.Presenter? = null

    override fun processData() {
        mPresenter = AuthPresenter(this)
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
                mPresenter?.getAccessToken(code)
                ToastUtils.show(mContext, code)
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

    override fun setPresenter(presenter: AuthContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun getContext(): Context {
        return mContext as Context
    }

    override fun setProgressDialogVisibility(flag: Boolean) {
        if (flag) {
            showProgressDialog(true)
        } else {
            hideProgressDialog()
        }
    }

    /**
     * 获取Token失败
     */
    override fun getAccessTokenFailed() {
        ToastUtils.show(mContext, R.string.a_auth_authorize_fail)
        finish()
    }

    /**
     * 获取Token成功
     */
    override fun getAccessTokenSuccess() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}

object AuthConfig {
    /**
     * Client_ID的Key
     */
    const val CLIENT_ID_KEY: String = "client_id"
    /**
     * Client_Secret的Key
     */
    const val CLIENT_SECRET_KEY: String = "client_secret"
    /**
     * 回调URI的KEY
     */
    const val REDIRECT_URI_KEY: String = "redirect_uri"
    /**
     * 回调类型
     */
    const val RESPONSE_TYPE_KEY: String = "response_type"
    /**
     * 返回码
     */
    const val RESPONSE_TYPE_CODE_KEY: String = "code"
    /**
     * Grant类型
     */
    const val GRANT_TYPE_KEY: String = "grant_type"
    /**
     * Grant的值
     */
    const val GRANT_TYPE_VALUE: String = "authorization_code"
}
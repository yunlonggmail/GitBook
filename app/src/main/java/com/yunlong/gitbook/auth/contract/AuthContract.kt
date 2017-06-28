package com.yunlong.gitbook.auth.contract

import com.yunlong.base.mvp.presenter.BasePresenter
import com.yunlong.base.mvp.view.BaseView
import com.yunlong.gitbook.auth.model.AccessToken

/**
 * Created by shiyunlong on 2017/6/27.
 * 验证合同
 */
interface AuthContract {
    /**
     * 主持人
     */
    interface Presenter : BasePresenter {
        /**
         * 获取AccessToken
         */
        fun getAccessToken(code: String?)
    }

    /**
     * View
     */
    interface View : BaseView<Presenter> {
        /**
         * 设置对话框
         */
        fun setProgressDialogVisibility(flag: Boolean)

        /**
         * 获取AccessToken成功
         */
        fun getAccessTokenSuccess()

        /**
         * 获取AccessToken失败
         */
        fun getAccessTokenFailed()
    }

}
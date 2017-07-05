package com.yunlong.gitbook.user.contract

import com.yunlong.base.mvp.presenter.BasePresenter
import com.yunlong.base.mvp.view.BaseView
import com.yunlong.gitbook.user.model.User

/**
 * Created by shiyunlong on 2017/7/5.
 * 用户联系人
 */
class UserContract {
    /**
     * View
     */
    interface View : BaseView<Presenter> {
        /**
         * 展示用户信息
         */
        fun showInfo(user: User?)

        /**
         * 获取消息失败
         */
        fun getInfoFailed()
    }

    /**
     * 主持人
     */
    interface Presenter : BasePresenter {
        /**
         * 获取信息
         */
        fun getInfo()
    }

}
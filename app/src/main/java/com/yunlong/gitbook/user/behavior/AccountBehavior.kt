package com.yunlong.gitbook.user.behavior

import com.yunlong.gitbook.auth.manager.GitBookAccountManager
import com.yunlong.gitbook.user.contract.UserContract
import com.yunlong.gitbook.user.model.User

/**
 * Created by shiyunlong on 2017/7/12.
 * 当前账号的行为
 */
class AccountBehavior : UserBehavior {

    override fun initData(view: UserContract.View, presenter: UserContract.Presenter?) {
        val mUser: User? = GitBookAccountManager.getAccountInfo()
        mUser?.let {
            view.showInfo(mUser)
        } ?: presenter?.getInfo()
    }
}
package com.yunlong.gitbook.user.behavior

import com.yunlong.gitbook.user.contract.UserContract

/**
 * Created by shiyunlong on 2017/7/12.
 * 正常类型
 */
class NormalBehavior : UserBehavior {
    /**
     * 正常行为，普通用户的行为
     */
    override fun initData(view: UserContract.View, presenter: UserContract.Presenter?) {
        presenter?.getInfo()
    }
}
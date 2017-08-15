package com.yunlong.gitbook.user.behavior

import com.yunlong.gitbook.user.contract.UserContract

/**
 * Created by shiyunlong on 2017/7/12.
 * 用户类型
 */
interface UserBehavior {
    /**
     * 初始化数据
     */
    fun initData(view: UserContract.View, presenter: UserContract.Presenter?)

}
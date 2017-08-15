package com.yunlong.gitbook.user.view

import com.yunlong.gitbook.user.behavior.NormalBehavior

/**
 * Created by shiyunlong on 2017/7/5.
 * 用户的Fragment
 */
class UserInformationFragment : BaseUserInformationFragment() {
    init {
        mBehavior = NormalBehavior()
    }

    companion object {
        fun newInstance(): UserInformationFragment {
            val fragment: UserInformationFragment = UserInformationFragment()
            return fragment;
        }
    }
}
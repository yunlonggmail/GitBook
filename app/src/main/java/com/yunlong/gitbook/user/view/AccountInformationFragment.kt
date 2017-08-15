package com.yunlong.gitbook.user.view

import com.yunlong.gitbook.user.behavior.AccountBehavior


/**
 * Created by shiyunlong on 2017/6/23.
 * 所有的Fragment
 */
class AccountInformationFragment : BaseUserInformationFragment() {

    init {
        mBehavior = AccountBehavior()
    }

    companion object {
        fun newInstance(): AccountInformationFragment {
            val fragment: AccountInformationFragment = AccountInformationFragment()
            return fragment;
        }
    }

}
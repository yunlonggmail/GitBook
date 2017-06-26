package com.yunlong.gitbook.main.fragment

import com.yunlong.base.mvp.fragment.BaseFragment
import com.yunlong.gitbook.R

/**
 * Created by shiyunlong on 2017/6/23.
 * 所有的Fragment
 */
class MyBookListFragment : BaseFragment(R.layout.f_my_book_list) {

    /**
     * 伴生对象
     */
    companion object {
        fun newInstance(): MyBookListFragment {
            val fragment = MyBookListFragment();
            return fragment;
        }
    }

    /**
     * 所有的BookList
     */
    override fun viewDidLoad() {
    }
}
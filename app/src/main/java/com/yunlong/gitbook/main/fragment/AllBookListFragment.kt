package com.yunlong.gitbook.main.fragment

import com.yunlong.base.mvp.fragment.BaseFragment
import com.yunlong.gitbook.R

/**
 * Created by shiyunlong on 2017/6/23.
 * 所有的Fragment
 */
class AllBookListFragment : BaseFragment(R.layout.f_all_book_list) {

    /**
     * 伴生对象
     */
    companion object {
        fun newInstance(): AllBookListFragment {
            val fragment = AllBookListFragment();
            return fragment;
        }
    }

    /**
     * 所有的BookList
     */
    override fun viewDidLoad() {
    }
}
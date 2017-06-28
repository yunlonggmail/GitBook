package com.yunlong.gitbook.main

import com.yunlong.gitbook.R
import com.yunlong.gitbook.main.fragment.AllBookListFragment
import com.yunlong.gitbook.main.fragment.MyBookListFragment
import com.yunlong.gitbook.user.view.MyInformationFragment

/**
 * Created by shiyunlong on 2017/6/26.
 * MainTab
 */
enum class MainTab(private var idx: Int,
                   private var resName: Int,
                   private var resIcon: Int,
                   private var clz: Class<*>) {

    ALL_BOOK_LIST(1, R.string.main_tab_all_book, R.drawable.tab_icon_home, AllBookListFragment::class.java),

    TOPICS_LIST(2, R.string.main_tab_my_book, R.drawable.tab_icon_insurance, MyBookListFragment::class.java),

    ME(3, R.string.main_tab_my_info, R.drawable.tab_icon_me, MyInformationFragment::class.java);


    fun getIdx(): Int {
        return idx
    }

    fun setIdx(idx: Int) {
        this.idx = idx
    }

    fun getResName(): Int {
        return resName
    }

    fun setResName(resName: Int) {
        this.resName = resName
    }

    fun getResIcon(): Int {
        return resIcon
    }

    fun setResIcon(resIcon: Int) {
        this.resIcon = resIcon
    }

    fun setClass(clz: Class<*>) {
        this.clz = clz
    }

    fun getClazz(): Class<*> {
        return clz
    }

}
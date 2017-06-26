package com.yunlong.base.mvp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.yunlong.base.mvp.fragment.BaseFragment

/**
 * Created by shiyunlong on 2017/6/26.
 * FragmentManager
 */
abstract class BaseVPFAdapter(fm: FragmentManager, val fragments: List<BaseFragment>?) : FragmentStatePagerAdapter(fm) {
    /**
     * 获取对应的条目
     */
    override fun getItem(i: Int): BaseFragment? {
        return fragments?.getOrNull(i)
    }

    /**
     * 获取数量
     */
    override fun getCount(): Int {
        return fragments?.size ?: 0
    }

    override fun getItemPosition(`object`: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }
}
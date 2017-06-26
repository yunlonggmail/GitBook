package com.yunlong.gitbook.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yunlong.base.mvp.adapter.BaseVPFAdapter
import com.yunlong.base.mvp.fragment.BaseFragment

/**
 * Created by shiyunlong on 2017/6/26.
 * 首页ViewPager适配器
 */
class MainFragmentAdapter(fm: FragmentManager, fragments: List<BaseFragment>?) : BaseVPFAdapter(fm, fragments) {

}
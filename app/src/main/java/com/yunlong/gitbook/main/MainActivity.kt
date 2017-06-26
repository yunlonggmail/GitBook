package com.yunlong.gitbook.main

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TabHost
import com.yunlong.base.mvp.activity.BaseActivity
import com.yunlong.gitbook.R
import kotlinx.android.synthetic.main.i_tool_bar.*;
import kotlinx.android.synthetic.main.a_main.*
import android.graphics.drawable.Drawable
import android.support.v4.view.ViewPager
import android.widget.TextView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TabWidget
import com.yunlong.base.mvp.fragment.BaseFragment
import com.yunlong.gitbook.main.fragment.AllBookListFragment
import com.yunlong.gitbook.main.fragment.MyBookListFragment
import com.yunlong.gitbook.main.fragment.MyInformationFragment


class MainActivity : BaseActivity(), TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    private var fragments: List<BaseFragment>? = null

    override fun getLayoutId(): Int {
        return R.layout.a_main
    }

    override fun initTitleBar() {
        toolbar.setTitle(R.string.tool_bar_title_main)
    }

    override fun initView() {
        initFragments()
        initTabs();
        initVp();
    }

    override fun initData() {
        Log.e(MainActivity::class.java.simpleName, "initData")
    }

    /**
     * 初始化Fragments
     */
    fun initFragments() {
        fragments = listOf(AllBookListFragment.newInstance(), MyBookListFragment.newInstance(), MyInformationFragment.newInstance())
    }

    /**
     * 初始化Tabs
     */
    fun initTabs() {
        mth_host.setup(this, supportFragmentManager, R.id.vp_fragments)
        mth_host.tabWidget.showDividers = 0
        mth_host.setOnTabChangedListener(this)
        val tabs = MainTab.values()
        for (tab in tabs) {
            val tabSpec: TabHost.TabSpec = mth_host.newTabSpec(getString(tab.getResName()))
            val indicator: View = LayoutInflater.from(mContext).inflate(R.layout.tab_resource, null)
            val title: TextView = indicator.findViewById(R.id.tab_title) as TextView
            val drawable: Drawable = this.resources.getDrawable(tab.getResIcon())
            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
            title.text = getString(tab.getResName())
            tabSpec.setIndicator(indicator)
            tabSpec.setContent(TabHost.TabContentFactory { View(this) })
            mth_host.addTab(tabSpec, tab.getClazz(), null)
        }
    }

    /**
     * 初始化ViewPager
     */
    fun initVp() {
        vp_fragments.addOnPageChangeListener(this)
        vp_fragments.adapter = (MainFragmentAdapter(supportFragmentManager, fragments))
    }

    /**
     * 当页面滚动的时候
     */
    override fun onPageScrolled(i: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(i: Int) {
        val tabWidget: TabWidget = mth_host.tabWidget
        val oldFocusability = tabWidget.descendantFocusability
        tabWidget.descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS
        mth_host.currentTab = i
        tabWidget.descendantFocusability = oldFocusability
    }

    override fun onPageScrollStateChanged(i: Int) {
    }

    override fun onTabChanged(tabId: String?) {
        val position = mth_host.currentTab
        vp_fragments.currentItem = position
        supportInvalidateOptionsMenu()
    }

}




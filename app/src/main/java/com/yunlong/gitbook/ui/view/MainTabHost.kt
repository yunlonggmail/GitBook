package com.yunlong.gitbook.ui.view

import android.content.Context
import android.support.v4.app.FragmentTabHost
import android.util.AttributeSet
import android.view.View

/**
 * Created by shiyunlong on 2017/6/26.
 * 自定义TabHost
 */
open class MainTabHost(context: Context, attrs: AttributeSet) : FragmentTabHost(context, attrs) {
    /**
     * 当前Tab
     */
    private var mCurrentTag: String? = null
    /**
     * 没有改变的Tag
     */
    private var mNoTabChangedTag: String? = null;

    /**
     * 当Tab变化的时候
     */
    override fun onTabChanged(tabId: String?) {
        if (tabId == mNoTabChangedTag) {
            setCurrentTabByTag(mCurrentTag);
        } else {
            super.onTabChanged(tabId)
            mCurrentTag = tabId
        }
    }

    /**
     * 设置没有Tab改变的监听

     * @param tag
     */
    fun setNoTabChangedTag(tag: String) {
        this.mNoTabChangedTag = tag
    }

    /**
     * 展示Icon
     */
    fun showNoticeIcon(id: Int) {
        val view = currentTabView
        val ivNotice = view!!.findViewById(id)
        ivNotice.visibility = View.VISIBLE
    }

}
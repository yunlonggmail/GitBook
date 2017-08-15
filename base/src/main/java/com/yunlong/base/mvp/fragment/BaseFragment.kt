package com.yunlong.base.mvp.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yunlong.base.R
import com.yunlong.base.view.GitBookDialog
import org.jetbrains.anko.find


/**
 * Created by shiyunlong on 2017/6/23.
 * Fragment基类
 */
abstract class BaseFragment(private val mResourceID: Int) : RxFragment() {
    /**
     * 填充器
     */
    var mLayoutInflater: LayoutInflater? = null
    /**
     *基类View
     */
    var mRootView: View? = null
    /**
     * 进度条
     */
    var mProgressDialog: GitBookDialog? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mLayoutInflater = inflater;
        mRootView = mLayoutInflater?.inflate(mResourceID, container, false);
        viewDidLoad()
        return mRootView
    }

    /**
     * View加载
     */
    abstract fun viewDidLoad()

    /**
     * findViewById简写
     */
    protected fun find(id: Int): View? {
        return mRootView?.find<View>(id)
    }

    /**
     * 展示对话框
     */
    fun showProgressDialog(cancelable: Boolean) {
        mProgressDialog?.let { mProgressDialog?.dismiss() }
        mProgressDialog = GitBookDialog(activity).show(getString(R.string.base_please_wait_for_a_later), cancelable)
    }

    /**
     * 隐藏Dialog
     */
    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog?.isShowing as Boolean) {
            mProgressDialog?.dismiss();
            mProgressDialog = null;
        }
    }

}
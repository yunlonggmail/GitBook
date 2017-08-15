package com.yunlong.base.mvp.activity

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yunlong.base.R
import com.yunlong.base.view.GitBookDialog
import kotlinx.android.synthetic.main.i_tool_bar.*
import org.jetbrains.anko.find

/**
 * Created by shiyunlong on 2017/6/23.
 * Activity基类
 */
abstract class BaseActivity : RxAppCompatActivity() {
    /**
     * 上下文
     */
    open var mContext: Context? = null
    /**
     * ToolBar
     */
    open var mToolBar: Toolbar? = null
    /**
     * 进度条
     */
    var mProgressDialog: GitBookDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(getLayoutId())
        activityInit()
    }


    abstract fun getLayoutId(): Int

    fun activityInit() {
        initTitleBar()
        initView()
        initData()
    }

    /**
     * 初始化TitleBar
     */
    protected open fun initTitleBar() {
        mToolBar = find<Toolbar>(R.id.toolbar)
    }

    /**
     * 初始化View
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 展示对话框
     */
    fun showProgressDialog(cancelable: Boolean) {
        mProgressDialog?.let { mProgressDialog?.dismiss() }
        mProgressDialog = GitBookDialog(this).show(getString(R.string.base_please_wait_for_a_later), cancelable)
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
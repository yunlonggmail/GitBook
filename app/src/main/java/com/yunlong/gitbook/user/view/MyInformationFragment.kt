package com.yunlong.gitbook.user.view

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Button
import com.yunlong.base.mvp.fragment.BaseFragment
import com.yunlong.base.util.ToastUtils
import com.yunlong.gitbook.R
import com.yunlong.gitbook.auth.manager.GitBookAccountManager
import com.yunlong.gitbook.auth.view.AuthActivity

/**
 * Created by shiyunlong on 2017/6/23.
 * 所有的Fragment
 */
class MyInformationFragment : BaseFragment(R.layout.f_my_information), View.OnClickListener {

    /**
     * 伴生对象
     */
    companion object {
        /**
         * 验证请求码
         */
        val REQUEST_CODE_AUTH: Int = 0x1001

        fun newInstance(): MyInformationFragment {
            val fragment = MyInformationFragment();
            return fragment;
        }
    }

    /**
     * 登录按钮
     */
    var btnConfirm: Button? = null

    /**
     * 所有的BookList
     */
    override fun viewDidLoad() {
        initView()
    }

    fun initView() {
        btnConfirm = find(R.id.btn_confirm) as Button?
        btnConfirm?.setOnClickListener(this)
    }

    /**
     * 点击
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_confirm -> auth()
        }
    }

    /**
     * 验证
     */
    fun auth() {
        if (!GitBookAccountManager.isLogin()) {
            val intent: Intent? = Intent(activity, AuthActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_AUTH)
        } else {
            ToastUtils.show(activity, "已登录：token->" + GitBookAccountManager.getAccessToken()?.access_token)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_AUTH -> {
                if (resultCode == Activity.RESULT_OK) {
                    ToastUtils.show(activity, GitBookAccountManager.getAccessToken()?.access_token)
                }
            }
        }
    }
}
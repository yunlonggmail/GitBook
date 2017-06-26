package com.yunlong.gitbook.main.fragment

import android.content.Intent
import android.view.View
import android.widget.Button
import com.yunlong.base.mvp.fragment.BaseFragment
import com.yunlong.gitbook.R
import com.yunlong.gitbook.auth.AuthActivity
import kotlinx.android.synthetic.main.f_my_information.*

/**
 * Created by shiyunlong on 2017/6/23.
 * 所有的Fragment
 */
class MyInformationFragment : BaseFragment(R.layout.f_my_information), View.OnClickListener {
    /**
     * 登录按钮
     */
    var btnConfirm: Button? = null

    /**
     * 伴生对象
     */
    companion object {
        fun newInstance(): MyInformationFragment {
            val fragment = MyInformationFragment();
            return fragment;
        }
    }

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

    fun auth() {
        val intent: Intent? = Intent(activity, AuthActivity::class.java)
        startActivity(intent)
    }
}
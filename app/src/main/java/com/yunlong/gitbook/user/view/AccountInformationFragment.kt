package com.yunlong.gitbook.user.view

import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.yunlong.base.mvp.fragment.BaseFragment
import com.yunlong.base.mvp.view.BaseView
import com.yunlong.base.util.ToastUtils
import com.yunlong.gitbook.R
import com.yunlong.gitbook.auth.manager.GitBookAccountManager
import com.yunlong.gitbook.auth.view.AuthActivity
import com.yunlong.gitbook.user.contract.UserContract
import com.yunlong.gitbook.user.model.User

/**
 * Created by shiyunlong on 2017/6/23.
 * 所有的Fragment
 */
class AccountInformationFragment : BaseFragment(R.layout.f_account_information), View.OnClickListener, UserContract.View {

    /**
     * 伴生对象
     */
    companion object {
        /**
         * 验证请求码
         */
        val REQUEST_CODE_AUTH: Int = 0x1001

        fun newInstance(): AccountInformationFragment {
            val fragment = AccountInformationFragment();
            return fragment;
        }
    }

    /**
     * 主持人
     */
    var mPresenter: UserContract.Presenter? = null

    /**
     * 登录按钮
     */
    var btnConfirm: Button? = null
    /**
     * tvInfo
     */
    var mTvInfo: TextView? = null

    /**
     * 所有的BookList
     */
    override fun viewDidLoad() {
        initView()
        initData()
    }

    fun initView() {
        mTvInfo = find(R.id.tv_info) as TextView?
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

    fun initData() {
        val mUser: User? = GitBookAccountManager.getAccountInfo()
        mUser?.let {
            showInfo(mUser)
        } ?: mPresenter?.getInfo()
    }

    override fun onResume() {
        super.onResume()
        val mUser: User? = GitBookAccountManager.getAccountInfo()
        mUser?.let { showInfo(mUser) }
    }

    override fun setPresenter(presenter: UserContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun setProgressDialogVisibility(flag: Boolean) {
        if (flag)
            showProgressDialog(true)
        else
            hideProgressDialog()
    }

    override fun showInfo(user: User?) {
        mTvInfo?.text = getString(R.string.f_account_info, user.toString())
    }

    override fun getInfoFailed() {
        mTvInfo?.text = getString(R.string.f_account_info, getString(R.string.f_account_info_failed))
    }

}
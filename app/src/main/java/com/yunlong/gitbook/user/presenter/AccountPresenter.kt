package com.yunlong.gitbook.user.presenter

import com.facebook.stetho.server.CompositeInputStream
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.yunlong.base.mvp.presenter.BasePresenter
import com.yunlong.base.util.StringUtils
import com.yunlong.gitbook.auth.api.AuthRepository
import com.yunlong.gitbook.auth.manager.GitBookAccountManager
import com.yunlong.gitbook.auth.model.AccessToken
import com.yunlong.gitbook.user.api.UserRepository
import com.yunlong.gitbook.user.contract.UserContract
import com.yunlong.gitbook.user.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by shiyunlong on 2017/7/5.
 * 账号主持人
 */
class AccountPresenter(val view: UserContract.View) : UserContract.Presenter {

    /**
     * 储存器
     */
    var mRepository: UserRepository? = null

    init {
        view.setPresenter(this)
        mRepository = UserRepository()
    }


    /**
     * 获取信息
     */
    override fun getInfo() {
        view.setProgressDialogVisibility(true)
        mRepository?.getAccountInfo()?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.compose((view as LifecycleProvider<FragmentEvent>).bindUntilEvent(FragmentEvent.DESTROY))
                ?.subscribe({ user ->
                    view.setProgressDialogVisibility(false)
                    val mUser: User? = user.body()
                    view.showInfo(mUser)
                    mUser?.let { GitBookAccountManager.setAccountInfo(mUser) }
                }, { throwable ->
                    view.setProgressDialogVisibility(false)
                    view.getInfoFailed()
                })
    }


    override fun start() {

    }


}
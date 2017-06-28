package com.yunlong.gitbook.auth.presenter

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.yunlong.base.util.StringUtils
import com.yunlong.gitbook.auth.api.AuthRepository
import com.yunlong.gitbook.auth.contract.AuthContract
import com.yunlong.gitbook.auth.manager.GitBookAccountManager
import com.yunlong.gitbook.auth.model.AccessToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by shiyunlong on 2017/6/27.
 * 验证主持人
 */
class AuthPresenter(val view: AuthContract.View) : AuthContract.Presenter {
    /**
     * 储存器
     */
    var mRepository: AuthRepository? = null

    init {
        view.setPresenter(this)
        mRepository = AuthRepository()
    }

    override fun start() {

    }

    override fun getAccessToken(code: String?) {
        view.setProgressDialogVisibility(true)
        mRepository?.getAccessToken(code)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.compose((view as LifecycleProvider<ActivityEvent>).bindUntilEvent(ActivityEvent.DESTROY))
                ?.subscribe({ accessTokenResponse ->
                    view.setProgressDialogVisibility(false)
                    val accessToken: AccessToken? = accessTokenResponse.body()
                    if (accessToken != null && !StringUtils.isEmpty(accessToken.access_token)) {
                        GitBookAccountManager.saveAccessToken(view.getContext(), accessToken)
                        view.getAccessTokenSuccess()
                    } else {
                        view.getAccessTokenFailed()
                    }
                }, { throwable ->
                    view.setProgressDialogVisibility(false)
                    view.getAccessTokenFailed()
                })
    }
}



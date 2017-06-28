package com.yunlong.base.mvp.activity

import android.net.MailTo
import android.net.Uri
import android.view.View
import android.webkit.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.yunlong.base.R
import com.yunlong.base.util.CallUtils
import com.yunlong.base.util.MailUtils
import kotlinx.android.synthetic.main.i_tool_bar.*
import org.jetbrains.anko.find


/**
 * Created by shiyunlong on 2017/6/26.
 * 基本的WebActivity
 */
abstract class BaseWebActivity : BaseActivity() {
    /**
     * WebView的父布局，主要是在销毁时方便销毁WebView
     */
    var llWebViewParent: LinearLayout? = null
    /**
     * WebView
     */
    open var mWebView: WebView? = null
    /**
     * 进度条
     */
    open var mPbWeb: ProgressBar? = null
    /**
     * 标题
     */
    open var mTitle: String? = ""
    /**
     * Url
     */
    open var mUrl: String? = ""
    /**
     * HtmlBody
     */
    open var mHtmlBody: String? = ""
    /**
     * 是否正在处理Url
     */
    private var mIsProcessUrl: Boolean = false
    /**
     * 是否需要处理Uri
     */
    protected var mNeedProcessUrl: Boolean = false

    override fun getLayoutId(): Int {
        return R.layout.a_base_web
    }

    override fun initTitleBar() {
        super.initTitleBar()
    }

    override fun initView() {
        llWebViewParent = find<LinearLayout>(R.id.ll_web_view_parent)
        mWebView = find<WebView>(R.id.wv)
        mPbWeb = find<ProgressBar>(R.id.pb_web)
    }

    override fun initData() {
        mTitle = intent?.getStringExtra(BaseWebConfig.KEY_TITLE)
        mUrl = intent?.getStringExtra(BaseWebConfig.KEY_URL)
        mHtmlBody = intent?.getStringExtra(BaseWebConfig.KEY_HTML)

        initSetting()
        loadData()

        mWebView?.setWebViewClient(BaseWebViewClient())
        mWebView?.setWebChromeClient(BaseWebChromeClient())
    }

    override fun onResume() {
        super.onResume()
        mIsProcessUrl = false
        mWebView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mWebView?.onPause()
    }

    override fun onDestroy() {
        if (mWebView != null && llWebViewParent != null) {
            llWebViewParent?.removeView(mWebView)
            mWebView?.stopLoading()
            mWebView?.removeAllViews()
            mWebView?.clearCache(true)
            mWebView?.visibility = View.GONE
            mWebView?.destroy()
        }
        super.onDestroy()
    }

    /**
     * 初始化设置
     */
    private fun initSetting() {
        var setting: WebSettings? = mWebView?.settings
        setting?.javaScriptEnabled = true //是否支持Js
        setting?.javaScriptCanOpenWindowsAutomatically = true//js能否自动打开窗口
        setting?.setSupportZoom(true)//是否支持缩放
        setting?.builtInZoomControls = false//创建缩放控制
        setting?.displayZoomControls = true//显示缩放控制
        setting?.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS//屏幕适应：值是内容大小
        setting?.useWideViewPort = true//屏幕适应的一部分
        setting?.loadWithOverviewMode = true//屏幕适应的一部分

        setting?.setGeolocationEnabled(true)//启动地理位置
        setting?.domStorageEnabled = true//是否存储Dom 最重要的方法，一定要设置，这就是出不来的主要原因
    }

    /**
     * 加载数据
     */
    fun loadData() {
        mUrl?.let {
            loadUrl(mWebView, mUrl);
        } ?: mHtmlBody?.let {
            loadHTMLZH(mHtmlBody)
        } ?: processData()

    }

    /**
     * 加载Url
     */
    protected fun loadUrl(view: WebView?, url: String?) {
        try {
            mIsProcessUrl = false
            view?.loadUrl(url)
        } catch (e: Exception) {
            val baseUrl = getString(R.string.a_base_web_site)
            view?.loadUrl(baseUrl)
        }
    }

    /**
     * 加载Html
     */
    private fun loadHTMLZH(htmlBody: String?) {
        mWebView?.loadDataWithBaseURL(null, htmlBody, BaseWebConfig.HTML_MIME_TYPE, BaseWebConfig.HTML_ENCODING, null)
    }

    /**
     * 设置Title
     */
    protected fun setTitleBar(title: String?) {
        mToolBar?.title = title
    }

    /**
     * 设置Title
     */
    protected fun setTitleBar(titleId: Int) {
        mToolBar?.title = resources.getString(titleId)
    }

    /**
     * 处理数据
     */
    abstract fun processData()

    /**
     * BaseWebViewClient
     */
    inner class BaseWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            processUrl(Uri.parse(url))
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            mTitle ?: setTitleBar(view?.title)
            super.onPageFinished(view, url)
        }
    }

    /**
     * Chrome客户端
     */
    inner class BaseWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            if (newProgress == 100) {
                mPbWeb?.visibility = View.GONE
            } else {
                if (mPbWeb?.visibility == View.GONE)
                    mPbWeb?.visibility = View.VISIBLE
                mPbWeb?.progress = newProgress
            }
            super.onProgressChanged(view, newProgress)
        }

        /**
         * 配置权限（同样在WebChromeClient中实现）
         * 魅族等某些手机要请求地理位置
         */
        override fun onGeolocationPermissionsShowPrompt(origin: String,
                                                        callback: GeolocationPermissions.Callback) {
            callback.invoke(origin, true, false)
            super.onGeolocationPermissionsShowPrompt(origin, callback)
        }

    }

    /**
     * 处理Url
     */
    open fun processUrl(uri: Uri?) {
        if (mIsProcessUrl)
            return
        else {
            mIsProcessUrl = true
            if (mNeedProcessUrl) {
                processOtherUri(uri)
            } else if (uri?.scheme?.equals(BaseWebConfig.SCHEME_NATIVE) as Boolean) {
                processNativeUrl(uri)
            } else if (uri.scheme?.equals(BaseWebConfig.SCHEME_TEL) as Boolean) {
                processTel(uri)
            } else if (MailTo.isMailTo(uri.toString())) {
                processMail(uri)
            } else {
                loadUrl(mWebView, uri.toString())
            }
        }
    }

    /**
     * 处理其他Url

     * @param uri
     */
    protected open fun processOtherUri(uri: Uri?) {

    }

    /**
     * 处理NativeUrl
     */
    protected open fun processNativeUrl(uri: Uri?) {

    }

    /**
     * 处理电话
     */
    protected fun processTel(uri: Uri?) {
        CallUtils.dial(mContext, uri)
    }

    /**
     * 处理Mail
     */
    protected fun processMail(uri: Uri?) {
        MailUtils.sendTo(mContext, uri)
    }
}

open class BaseWebConfig {
    companion object {
        /**
         * 标题
         */
        val KEY_TITLE: String = "key_title"
        /**
         * Url
         */
        val KEY_URL: String = "key_url"
        /**
         * 加载Html
         */
        val KEY_HTML: String = "key_html"
        /**
         * 类型
         */
        val HTML_MIME_TYPE = "text/html"
        /**
         * 编码
         */
        val HTML_ENCODING = "utf-8"
        /**
         * NativeScheme
         */
        val SCHEME_NATIVE = "native"
        /**
         * 电话Scheme
         */
        val SCHEME_TEL = "tel"
    }
}
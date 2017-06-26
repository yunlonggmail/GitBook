package com.yunlong.base.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.security.AccessControlContext

/**
 * Created by shiyunlong on 2017/6/26.
 * 拨打电话工具类
 */
object CallUtils {
    /**
     * 拨号
     */
    fun dial(context: Context?, uri: Uri?) {
        val intent: Intent = Intent(Intent.ACTION_DIAL, uri)
        context?.startActivity(intent)
    }
}
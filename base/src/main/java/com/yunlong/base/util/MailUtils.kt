package com.yunlong.base.util

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Created by shiyunlong on 2017/6/26.
 * MailUtils
 */
object MailUtils {
    /**
     * 发送邮件
     */
    fun sendTo(context: Context?, uri: Uri?) {
        val intent: Intent = Intent(Intent.ACTION_SENDTO, uri)
        context?.startActivity(intent);
    }

}
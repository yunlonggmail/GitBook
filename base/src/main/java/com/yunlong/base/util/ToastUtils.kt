package com.yunlong.base.util

import android.content.Context
import android.widget.Toast

/**
 * Created by shiyunlong on 2017/6/26.
 * Toast工具类
 */
object ToastUtils {
    /**
     * 展示Toast
     */
    fun show(context: Context?, str: String?) {
        str?.let { Toast.makeText(context, str, Toast.LENGTH_SHORT).show() }
    }

    /**
     * 展示Toast
     */
    fun show(context: Context?, strId: Int) {
        if (strId > 0) {
            Toast.makeText(context, strId, Toast.LENGTH_SHORT).show()
        }
    }

}
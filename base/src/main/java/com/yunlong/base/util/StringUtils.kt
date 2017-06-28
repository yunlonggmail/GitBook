package com.yunlong.base.util

import android.text.TextUtils



/**
 * Created by shiyunlong on 2017/6/28.
 * 字符串工具类
 */
object StringUtils {
    /**
     * 针对api返回字符串是否为"空"

     * @param dstString
     * *
     * @return
     */
    fun isEmpty(dstString: String): Boolean {
        return TextUtils.isEmpty(dstString) || "null" == dstString
    }
}
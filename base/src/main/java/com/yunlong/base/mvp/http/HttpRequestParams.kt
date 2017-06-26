package com.yunlong.base.mvp.http

import java.net.URLEncoder


/**
 * Created by shiyunlong on 2017/6/26.
 * 请求参数
 */
open class HttpRequestParams {

    var parameters = mutableMapOf<String, Any>()
    /**
     * 添加参数
     */
    open fun addParameter(name: String, value: Any): HttpRequestParams {
        parameters[name] = value
        return this
    }

    /**
     * 获取String
     */
    open fun getString(): String {
        val encodeParameters: StringBuilder = StringBuilder()
        val paramsEncoding: String = getParamsEncoding()
        val entries: Set<Map.Entry<String, Any>>? = parameters.entries
        entries?.let {
            if (entries.isNotEmpty()) {
                val iterator: Iterator<Map.Entry<String, Any>> = entries.iterator()
                encodeParameters.append("?")
                while (iterator.hasNext()) {
                    val entry: Map.Entry<String, Any> = iterator.next()
                    encodeParameters.append(URLEncoder.encode(entry.key, paramsEncoding))
                    encodeParameters.append("=")
                    encodeParameters.append(URLEncoder.encode(entry.value.toString(), paramsEncoding))
                    if (iterator.hasNext())
                        encodeParameters.append("&")
                }
                return encodeParameters.toString()
            } else {
                return ""
            }
        }
        return ""
    }

    /**
     * 得到参数编码
     */
    private fun getParamsEncoding(): String {
        return "UTF-8"
    }
}
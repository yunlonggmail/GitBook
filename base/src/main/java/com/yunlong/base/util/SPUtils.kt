package com.yunlong.base.util

import android.content.Context
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE


/**
 * Created by shiyunlong on 2017/6/28.
 * SPUtils
 */
object SPUtils {

    val DEFAULT_XML_NAME = "git_book_config"

    /**
     * 保存字符串到默认SP-XML文件中

     * @param context：上下文
     * *
     * @param key：KEY，关键字
     * *
     * @param value：保存的值
     */
    fun saveDefaultStrPre(context: Context, key: String, value: String) {
        saveStrPre(context, DEFAULT_XML_NAME, key, value)
    }

    /**
     * 保存字符串到SP-XML文件中

     * @param context：上下文
     * *
     * @param name：名称，XML文件的名称
     * *
     * @param key：KEY，关键字
     * *
     * @param value：保存的值
     */
    fun saveStrPre(context: Context, name: String, key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * 获取默认SP-XML中对应的字符串

     * @param context：上下文
     * *
     * @param key：KEY，关键字
     * *
     * @param defaultValue：默认保存的值
     * *
     * @return :KEY对应的Value
     */
    fun getDefaultStrPre(context: Context, key: String, defaultValue: String): String {
        return getStrPre(context, DEFAULT_XML_NAME, key, defaultValue)
    }

    /**
     * 获取SP-XML中对应的字符串

     * @param context：上下文
     * *
     * @param name：名称，XML文件的名称
     * *
     * @param key：KEY，关键字
     * *
     * @param defaultValue：默认值
     * *
     * @return :KEY对应的Value
     */
    fun getStrPre(context: Context, name: String, key: String, defaultValue: String): String {
        val sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, defaultValue)
    }
}
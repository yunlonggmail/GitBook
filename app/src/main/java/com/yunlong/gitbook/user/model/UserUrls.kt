package com.yunlong.gitbook.user.model

import java.lang.reflect.Field

/**
 * Created by shiyunlong on 2017/7/5.
 * 用户的Urls
 */
class UserUrls {
    /**
     * 详情页
     */
    var profile: String = ""
    /**
     * 收藏页
     */
    var stars: String = ""
    /**
     * 头像
     */
    var avatar: String = ""

    override fun toString(): String {
        val sb: StringBuilder? = StringBuilder()
        val fields: Array<Field> = this.javaClass.declaredFields
        sb?.append("{\n")
        for (field in fields) {
            field.isAccessible = true
            sb?.append(field.name)
            sb?.append(":")
            sb?.append(field.get(this))
            sb?.append("\n")
        }
        sb?.append("}")
        return sb.toString()
    }
}
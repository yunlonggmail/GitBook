package com.yunlong.gitbook.user.model

import java.lang.reflect.Field

/**
 * Created by shiyunlong on 2017/7/5.
 * 用户Auth
 */
class UserAuth {
    /**
     * Token
     */
    var token: String = ""
    /**
     * 密码
     */
    var password: Boolean = false
    /**
     * 已查证的
     */
    var verified: Boolean = false
    /**
     * 有效的
     */
    var validating: Boolean = false

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
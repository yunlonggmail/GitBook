package com.yunlong.gitbook.user.model

import java.lang.reflect.Field

/**
 * Created by shiyunlong on 2017/7/5.
 * 用户的Github的信息
 */
class UserGithub {
    /**
     * 用户名称
     */
    var username: String = ""
    /**
     * 状态信息
     */
    var scopes: List<String>? = null
    /**
     * ID
     */
    var installation_id: Long = -1
    /**
     * 必要的，不知道啥意思
     */
    var required: Boolean = false

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
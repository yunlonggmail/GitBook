package com.yunlong.gitbook.user.model

import java.lang.reflect.Field

/**
 * Created by shiyunlong on 2017/7/5.
 * 用户权限
 */
class UserPermissions {
    /**
     * 编辑权限
     */
    var edit: Boolean = false
    /**
     * 管理员权限
     */
    var admin: Boolean = false

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